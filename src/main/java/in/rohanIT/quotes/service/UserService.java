package in.rohanIT.quotes.service;

import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.rohanIT.quotes.entity.UserEntity;
import in.rohanIT.quotes.repo.UserRepo;
import in.rohanIT.quotes.userdto.City;
import in.rohanIT.quotes.userdto.Country;
import in.rohanIT.quotes.userdto.State;
import in.rohanIT.quotes.userdto.UserDto;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo ;

	@Autowired
	JavaMailSender mailSender;

//	@Autowired
//	UserEntity userEntity;
	
	private static final int tempPwLength=12;
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
	
	public String register(UserDto userDto) {
		
		if(userRepo.findByEmail(userDto.getEmail())!=null) {
			return "User already exist";
		}
		UserEntity userEntity=new UserEntity();
		userEntity.setName(userDto.getName());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setPwChanged(false);
		userEntity.setCountry(userDto.getCountry());
		userEntity.setState(userDto.getState());
		userEntity.setCity(userDto.getCity());
		
		String tempPw=generateRandomPw();
		userEntity.setPw(tempPw);
		try {
			sendWelcomeEmail(userDto,tempPw);
			return "Email sent to registered email id";
		}
		catch(Exception e){
			System.out.println(e);
			return "Registration done, but failed to send email. Contact support.";
        }
		finally {
			userRepo.save(userEntity);
		}
		
	}

	private void sendWelcomeEmail(UserDto userDto, String pw) throws MessagingException {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage, true);
		helper.setTo(userDto.getEmail());
		helper.setSubject("Welcome! Your temp pw");
		String htmlBody = """
                <html>
                <body>
                    <h2>Welcome, %s!</h2>
                    <p>Your account has been created successfully.</p>
                    <p><strong>Temporary Password:</strong> %s</p>
                    <p><strong>Important:</strong> For security reasons, please log in and change your password immediately.</p>
                    <br>
                    <p>Best regards,<br>Your Quotes App Team</p>
                </body>
                </html>
                """.formatted(userDto.getName() != null ? userDto.getName() : "User", pw);

        helper.setText(htmlBody, true); // true = html

        mailSender.send(mimeMessage);
	}

	private String generateRandomPw() {
		SecureRandom sc=new SecureRandom();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<tempPwLength;i++) {
			int indx=sc.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(indx));
		}
		return sb.toString();
	}

	public String login(UserDto userDto, String pw) {
		UserEntity userEntity=new UserEntity();
		if(userDto.getEmail()!=null) {
			String email= userDto.getEmail();
			userEntity=userRepo.findByEmail(email);
			if(userRepo.findByEmail(email) ==null) {
				return "Email doesn't exist";
			}
			if(userEntity.getPw() != pw) {
				return "Wrong pw";
			}
			
			return "login seccussfull";
		}
		return "please enter email";
	}
	
	private List<Country> countryCache;
	

	@PostConstruct
	public void loadData() {
	    try {
	        ObjectMapper mapper = new ObjectMapper();

	        InputStream is = getClass()
	                .getClassLoader()
	                .getResourceAsStream("countries-states-cities.json");

	        countryCache = mapper.readValue(
	                is,
	                new TypeReference<List<Country>>() {}
	        );

	        System.out.println("Data Loaded Successfully!");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public List<String> getCountriesList() {
	    List<String> list = new ArrayList<>();

	    for (Country c : countryCache) {
	        list.add(c.getName());
	    }

	    return list;
	}
	
	public List<String> getStates(String countryName) {

	    List<String> statesList = new ArrayList<>();

	    for (Country c : countryCache) {

	        if (c.getName().equalsIgnoreCase(countryName)) {

	            for (State s : c.getStates()) {
	                statesList.add(s.getName());
	            }
	        }
	    }

	    return statesList;
	}
	public List<String> getCities(String stateName) {

	    List<String> cityList = new ArrayList<>();

	    for (Country c : countryCache) {

	        for (State s : c.getStates()) {

	            if (s.getName().equalsIgnoreCase(stateName)) {

	                for (City city : s.getCities()) {
	                    cityList.add(city.getName());
	                }
	            }
	        }
	    }

	    return cityList;
	}

}
