package be.kdg.spacecrack;


import be.kdg.spacecrack.Exceptions.UserNotFoundException;
import be.kdg.spacecrack.controllers.TokenController;
import be.kdg.spacecrack.model.AccessToken;
import be.kdg.spacecrack.model.User;
import be.kdg.spacecrack.utilities.HibernateUtil;
import be.kdg.spacecrack.utilities.ITokenValueGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

import static junit.framework.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Tim on 3/02/14.
 */

@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginTests {

    @Autowired
    private ServletContext servletContext;
    private WebApplicationContext ctx;
    private MockMvc mockMvc;
    private TokenController tokenController;
    private User testUser;
    private ITokenValueGenerator mockTokenGenerator;

    @Before
    public void setUp() throws Exception {
       ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            mockMvc = webAppContextSetup(ctx).build();

        mockTokenGenerator = Mockito.mock(ITokenValueGenerator.class);
        tokenController = new TokenController(mockTokenGenerator);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        testUser = new User("testUsername", "testPassword");
        session.saveOrUpdate(testUser);
        tx.commit();

    }

  /* @Test
    public void testLoginIntegrated() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/tokens").param("username", "testUser").param("password", "testPassword")).andExpect(jsonPath("token", notNullValue()));
    }*/

   @Test
    public void testRequestAccessToken_ValidUser_Ok()
    {

        String name ="testUsername";
        String pw = "testPassword";
        User user = new User(name, pw);
        String expectedTokenValue = "testtokenvalue1234";
        Mockito.stub(mockTokenGenerator.generateToken()).toReturn(expectedTokenValue);
        AccessToken token = tokenController.getToken(user);


        assertEquals("Token value should be testtokenvalue1234", expectedTokenValue, token.getValue() );
    }

    @Test(expected = UserNotFoundException.class)
    public void testRequestAccessToken_InvalidUser_UserNotFoundException()
    {

        String name ="badUser";
        String pw = "badPw";
        User user = new User(name, pw);
        tokenController.getToken(user);

    }

    @Test
    public void integrationTestLogin_ValidUser_Token() throws Exception {
       ObjectMapper objectMapper = new ObjectMapper();
        String userjson = objectMapper.writeValueAsString(testUser);
        System.out.println("Userjson : " + userjson);

mockMvc.perform(post("/accesstokens/request").contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"testUsername\",\"password\":\"testPassword\"}").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.value", CoreMatchers.notNullValue())).andExpect(jsonPath("$.value", CoreMatchers.not("")));


    }

    @After
    public void tearDown() throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

     session.delete(testUser);
        tx.commit();


    }
}
