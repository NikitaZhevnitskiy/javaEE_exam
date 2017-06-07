import ejb.UserEJB;
import entity.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class LoginController implements Serializable {
    @EJB
    private UserEJB userEJB;

    private String formUserName;
    private String formPassword;
    private String formConfirmPassword;
    private String formFirstName;
    private String formMiddleName;
    private String formLastName;
    private Boolean formChef=false;

    /**
     * The current user registered in this session
     */
    private String registeredUser;
    private Boolean chef=false;


    public LoginController() {}

    public boolean isLoggedIn(){
        return registeredUser != null;
    }

    public String getRegisteredUser(){
        return registeredUser;
    }

    public User getUser(){
        return userEJB.getUser(registeredUser);
    }

    public String logOut(){
        registeredUser=null;
        formChef=false;
        chef=false;
        return "home.jsf";
    }


    public String logIn(){
        boolean valid = userEJB.login(formUserName, formPassword);
        if(valid){
            registeredUser = formUserName;
            chef= userEJB.getUser(registeredUser).getChef();
            return "home.jsf";
        } else {
            return "login.jsf";
        }
    }

    public String registerNew(){

        if(! formPassword.equals(formConfirmPassword)){
            return "newUser.jsf";
        }

        boolean registered = false;

        try {
            registered = userEJB.createUser(formUserName, formPassword, formFirstName, formMiddleName, formLastName, formChef);
        } catch (Exception e){
        }

        if(registered){
            registeredUser = formUserName;
            chef=formChef;
            return "home.jsf";
        } else {
            return "newUser.jsf";
        }
    }

    public String getFormUserName() {
        return formUserName;
    }

    public void setFormUserName(String formUserName) {
        this.formUserName = formUserName;
    }

    public String getFormPassword() {
        return formPassword;
    }

    public void setFormPassword(String formPassword) {
        this.formPassword = formPassword;
    }

    public String getFormConfirmPassword() {
        return formConfirmPassword;
    }

    public void setFormConfirmPassword(String formConfirmPassword) {
        this.formConfirmPassword = formConfirmPassword;
    }

    public String getFormFirstName() {
        return formFirstName;
    }

    public void setFormFirstName(String formFirstName) {
        this.formFirstName = formFirstName;
    }

    public String getFormMiddleName() {
        return formMiddleName;
    }

    public void setFormMiddleName(String formMiddleName) {
        this.formMiddleName = formMiddleName;
    }

    public String getFormLastName() {
        return formLastName;
    }

    public void setFormLastName(String formLastName) {
        this.formLastName = formLastName;
    }

    public Boolean getFormChef() {return formChef;}

    public void setFormChef(Boolean formChef) {this.formChef = formChef;}

    public Boolean getChef() {return chef;}
}
