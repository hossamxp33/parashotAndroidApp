package com.codesroots.hossam.parashotApp.entities;

import java.util.List;

public class DeliveryComments {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : حازم
         * personal_id : 454545
         * personal_id_image :
         * personal_license_image :
         * bank_accounts : 8888
         * phone : 010
         * alternative_phone : 010
         * gender : male
         * code : 0
         * created : null
         * modified : null
         * delivery_long : 31.2099091
         * delivery_lat : 30.0384395
         * photo : http://parashot.codesroots.com/library/osama.jpeg
         * deliveryrates : [{"id":1,"rate":3,"comments":"good","amount_outstanding":2,"user_id":113,"created":"2018-11-27T13:23:41+0000","modified":"2018-11-27T13:23:41+0000","delivry_id":1,"user":{"id":113,"username":"admin","photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png","phone":"sssasss","email":"sssasss"}}]
         */

        private int id;
        private String name;
        private int personal_id;
        private String personal_id_image;
        private String personal_license_image;
        private String bank_accounts;
        private String phone;
        private String alternative_phone;
        private String gender;
        private int code;
        private Object created;
        private Object modified;
        private String delivery_long;
        private String delivery_lat;
        private String photo;
        private List<DeliveryratesBean> deliveryrates;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPersonal_id() {
            return personal_id;
        }

        public void setPersonal_id(int personal_id) {
            this.personal_id = personal_id;
        }

        public String getPersonal_id_image() {
            return personal_id_image;
        }

        public void setPersonal_id_image(String personal_id_image) {
            this.personal_id_image = personal_id_image;
        }

        public String getPersonal_license_image() {
            return personal_license_image;
        }

        public void setPersonal_license_image(String personal_license_image) {
            this.personal_license_image = personal_license_image;
        }

        public String getBank_accounts() {
            return bank_accounts;
        }

        public void setBank_accounts(String bank_accounts) {
            this.bank_accounts = bank_accounts;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAlternative_phone() {
            return alternative_phone;
        }

        public void setAlternative_phone(String alternative_phone) {
            this.alternative_phone = alternative_phone;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public Object getCreated() {
            return created;
        }

        public void setCreated(Object created) {
            this.created = created;
        }

        public Object getModified() {
            return modified;
        }

        public void setModified(Object modified) {
            this.modified = modified;
        }

        public String getDelivery_long() {
            return delivery_long;
        }

        public void setDelivery_long(String delivery_long) {
            this.delivery_long = delivery_long;
        }

        public String getDelivery_lat() {
            return delivery_lat;
        }

        public void setDelivery_lat(String delivery_lat) {
            this.delivery_lat = delivery_lat;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public List<DeliveryratesBean> getDeliveryrates() {
            return deliveryrates;
        }

        public void setDeliveryrates(List<DeliveryratesBean> deliveryrates) {
            this.deliveryrates = deliveryrates;
        }

        public static class DeliveryratesBean {
            /**
             * id : 1
             * rate : 3
             * comments : good
             * amount_outstanding : 2
             * user_id : 113
             * created : 2018-11-27T13:23:41+0000
             * modified : 2018-11-27T13:23:41+0000
             * delivry_id : 1
             * user : {"id":113,"username":"admin","photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png","phone":"sssasss","email":"sssasss"}
             */

            private int id;
            private int rate;
            private String comments;
            private int amount_outstanding;
            private int user_id;
            private String created;
            private String modified;
            private int delivry_id;
            private UserBean user;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
            }

            public String getComments() {
                return comments;
            }

            public void setComments(String comments) {
                this.comments = comments;
            }

            public int getAmount_outstanding() {
                return amount_outstanding;
            }

            public void setAmount_outstanding(int amount_outstanding) {
                this.amount_outstanding = amount_outstanding;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getModified() {
                return modified;
            }

            public void setModified(String modified) {
                this.modified = modified;
            }

            public int getDelivry_id() {
                return delivry_id;
            }

            public void setDelivry_id(int delivry_id) {
                this.delivry_id = delivry_id;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class UserBean {
                /**
                 * id : 113
                 * username : admin
                 * photo : http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png
                 * phone : sssasss
                 * email : sssasss
                 */

                private int id;
                private String username;
                private String photo;
                private String phone;
                private String email;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }
            }
        }
    }
}
