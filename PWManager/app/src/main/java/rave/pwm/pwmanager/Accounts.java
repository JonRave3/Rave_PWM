package rave.pwm.pwmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Accounts {

    public static List<AccountRecord> ACCOUNTS = new ArrayList<AccountRecord>();
    public static final Map<String, AccountRecord> ACCT_MAP = new HashMap<String, AccountRecord>();

    private static void addRecord(AccountRecord ar){
        ACCOUNTS.add(ar);
        ACCT_MAP.put(ar.id, ar);
    }

    public static List<AccountRecord> testData(){

        ACCOUNTS.add(new AccountRecord("0", "Discover", "jravelo418", "123VearoL!@#", "n/a"));
        return ACCOUNTS;
    }

    public static class AccountRecord {
        public final String id;
        private String name;
        private String username;
        private String password;
        private String options;

        public AccountRecord(String id, String n, String un, String pw, String o) {
            this.id = id;
            this.name = n;
            this.username = un;
            this.password = pw;
            this.options = o;
        }


        public void update(String...params) {
            if(!params[0].isEmpty()){
                this.name = params[0];
            }
            if(!params[1].isEmpty()){
                this.username = params[1];
            }
            if(!params[2].isEmpty()){
                this.password = params[2];
            }
            if(!params[3].isEmpty()){
                this.options = params[3];
            }
        }
        public String getName(){
            return this.name;
        }
        public String getUserName(){
            return this.username;
        }
        public String getPassword() {
            return this.password;
        }
        public String getOptions() {
            return this.options;
        }
    }
}
