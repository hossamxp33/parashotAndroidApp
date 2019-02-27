package com.codesroots.hossam.parashotApp.entities;

public class RegisterResponseModel {

    /**
     * success : true
     * data : {"id":243,"group":null,"username":"237asd","photo":null,"code":200,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI0MywiZXhwIjoxNTUxODA0NTE0fQ.dVJ6fKiPjDUp1ObTgt90nHWE6pQgP1iKGzujPM2P1pY"}
     */

    private boolean success;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 243
         * group : null
         * username : 237asd
         * photo : null
         * code : 200
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI0MywiZXhwIjoxNTUxODA0NTE0fQ.dVJ6fKiPjDUp1ObTgt90nHWE6pQgP1iKGzujPM2P1pY
         */

        private int id;
        private int group;
        private String username;
        private Object photo;
        private int code;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGroup() {
            return group;
        }

        public void setGroup(int group) {
            this.group = group;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getPhoto() {
            return photo;
        }

        public void setPhoto(Object photo) {
            this.photo = photo;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
