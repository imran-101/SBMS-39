package com.userimran.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userimran.dto.LoginDto;
import com.userimran.dto.QuoteDto;
import com.userimran.dto.RegisterDto;
import com.userimran.dto.ResetPwdDto;
import com.userimran.dto.UserDto;
import com.userimran.entity.CityEntity;
import com.userimran.entity.CountryEntity;
import com.userimran.entity.StateEntity;
import com.userimran.entity.UserEntity;
import com.userimran.repo.CityRepo;
import com.userimran.repo.CountryRepo;
import com.userimran.repo.StateRepo;
import com.userimran.repo.UserRepo;
import com.userimran.utils.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private EmailUtils emailUtils;

	private QuoteDto[] quotations;

	@Override
	public Map<Integer, String> getCountries() {

		Map<Integer, String> countryMap = new HashMap<>();

		List<CountryEntity> countriesList = countryRepo.findAll();

		countriesList.forEach(c -> {
			countryMap.put(c.getCountryId(), c.getCountryName());
		});

		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer cid) {

		HashMap<Integer, String> statesMap = new HashMap<>();

		/*
		 * CountryEntity country=new CountryEntity(); country.setCountryId(cid);
		 * 
		 * StateEntity state=new StateEntity(); state.setCountry(country);
		 * 
		 * Example<StateEntity> of = Example.of(state);
		 * 
		 * List<StateEntity> statesList = stateRepo.findAll(of);
		 */
		List<StateEntity> statesList = stateRepo.getStates(cid);
		statesList.forEach(s -> {
			statesMap.put(s.getStateId(), s.getStateName());
		});

		return statesMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer sid) {

		HashMap<Integer, String> citiesMap = new HashMap<>();

		List<CityEntity> citiesList = cityRepo.getCities(sid);
		citiesList.forEach(c -> {
			citiesMap.put(c.getCityId(), c.getCityName());
		});
		return citiesMap;
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepo.findByEmail(email);

		/*
		 * UserDto dto = new UserDto(); BeanUtils.copyProperties(userEntity, dto);
		 */

		if (userEntity == null) {
			return null;
		}

		ModelMapper mapper = new ModelMapper();
		UserDto userDto = mapper.map(userEntity, UserDto.class);

		return userDto;
	}

	@Override
	public boolean regiterUser(RegisterDto regDto) {

		ModelMapper mapper = new ModelMapper();
		UserEntity entity = mapper.map(regDto, UserEntity.class);

		CountryEntity country = countryRepo.findById(regDto.getCountryId()).orElseThrow();

		StateEntity state = stateRepo.findById(regDto.getStateId()).orElseThrow();

		CityEntity city = cityRepo.findById(regDto.getCityId()).orElseThrow();

		entity.setCountry(country);
		entity.setState(state);
		entity.setCity(city);

		entity.setPwd(generateRandom());
		entity.setPwdUpdated("no");

		UserEntity savedEntity = userRepo.save(entity);

		String subject = "User Registeration";
		String body = "Your Temporary Password is " + entity.getPwd();

		emailUtils.sendEmail(regDto.getEmail(), subject, body);

		return savedEntity.getUserId() != null;
	}

	@Override
	public UserDto getUser(LoginDto loginDto) {
		UserEntity userEntity = userRepo.findByEmailAndPwd(loginDto.getEmail(), loginDto.getPwd());

		if (userEntity == null) {
			return null;
		}

		ModelMapper mapper = new ModelMapper();
		return mapper.map(userEntity, UserDto.class);
	}

	@Override
	public boolean resetPwd(ResetPwdDto pwdDto) {

		UserEntity userEntity = userRepo.findByEmailAndPwd(pwdDto.getEmail(), pwdDto.getOldPwd());
		if (userEntity != null) {
			userEntity.setPwd(pwdDto.getNewPwd());
			userEntity.setPwdUpdated("yes");
			userRepo.save(userEntity);
			return true;
		}
		return false;
	}

	@Override
	public String getQuote() {
		if (quotations == null) {
			String url = "https://type.fit/api/quotes";
			// web service call
			RestTemplate rt = new RestTemplate();
			ResponseEntity<String> forEntity = rt.getForEntity(url, String.class);
			String responseBody = forEntity.getBody();
			ObjectMapper mapper = new ObjectMapper();
			try {
				quotations = mapper.readValue(responseBody, QuoteDto[].class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Random r = new Random();
		int index = r.nextInt(quotations.length - 1);

		return quotations[index].getText();
	}

	private static String generateRandom() {
		String aToZ = "ABCDEFGHIJKLMNOPQRRSTUVWXYZ123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			int randomIndex = random.nextInt(aToZ.length());
			sb.append(aToZ.charAt(randomIndex));
		}
		return sb.toString();
	}

}
