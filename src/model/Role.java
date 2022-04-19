package model;

import java.io.Serializable;

public class Role implements Serializable {

    public enum RoleNameUser {
        ADMIN(0.5), USER(1.0);

        private final double value;

        RoleNameUser(double value) {
            this.value = value;
        }

        public double getRoleNameUser() {
            return this.value;
        }
    }

    public enum RoleNameComputer {
        NORMAL(1000000), VIP(2000000);

        private final double value;

        RoleNameComputer(double value) {
            this.value = value;
        }

        public double getRoleNameComputer() {
            return this.value;
        }
    }
}
