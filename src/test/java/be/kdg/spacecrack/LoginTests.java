package be.kdg.spacecrack;


import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Tim on 3/02/14.
 */

@ContextConfiguration({"classpath*:application-context.xml"})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginTests {

    @Autowired
    private ServletContext servletContext;
    private WebApplicationContext ctx;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
       ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            mockMvc = webAppContextSetup(ctx).build();
            }

   /* @Test
    public void testLoginIntegrated() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/tokens").param("username", "testUser").param("password", "testPassword")).andExpect(jsonPath("token", notNullValue()));
    }*/

  /*  @Test
    public void testLogin()
    {
        TokenController controller = new TokenController();
        String name ="testUsername";
        String pw = "testPassword";

        controller.getToken(name, pw);
    }*/
}
