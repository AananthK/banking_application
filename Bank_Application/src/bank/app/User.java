package bank.app;/** * * @author Aananth */public abstract class User {    private final String username;    private String password;    protected String role;        public User (String uName, String pWord){        username = uName;        password = pWord;    }        protected String getUsername(){        return this.username;    }        protected String getPassword(){        return this.password;    }        protected String getRole(){        return this.role;    }    }    