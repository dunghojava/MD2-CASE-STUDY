package model;

import java.io.Serializable;

public class Role implements Serializable {
    public enum RoleNameUser {
        ADMIN, USER;
    }
    public enum RoleNameComputer {
        NORMAL(1000000), VIP(2000000);

        private double value;

        RoleNameComputer(double value) {
            this.value = value;
        }
        public double getRoleNameComputer() {
            return this.value;
        }
    }

   public static void main(String[] args) {
       for (RoleNameComputer roleNameComputer: RoleNameComputer.values()) {
           System.out.println(roleNameComputer + " = " + roleNameComputer.value);
       }
   }
}
