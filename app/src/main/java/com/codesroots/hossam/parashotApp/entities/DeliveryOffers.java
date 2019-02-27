package com.codesroots.hossam.parashotApp.entities;

import java.util.List;

public class DeliveryOffers {


    private List<OffersBean> offers;

    public List<OffersBean> getOffers() {
        return offers;
    }

    public void setOffers(List<OffersBean> offers) {
        this.offers = offers;
    }

    public static class OffersBean {
        /**
         * id : 21
         * order_id : 54
         * offer : 58
         * created : 2019-02-27T09:52:59+0000
         * modified : 2019-02-27T09:52:59+0000
         * delivry_id : 1
         * delivry : {"id":1,"name":"حازم","user_id":237,"personal_id_image":"","personal_license_image":"","bank_accounts":"8888","phone":"010","alternative_phone":"010","gender":"male","code":0,"created":null,"modified":null,"delivery_long":"31.2099091","delivery_lat":"30.0384395","photo":"http://parashot.codesroots.com/library/osama.jpeg","total":4000,"total_rating":[{"delivry_id":1,"stars":24,"count":8}],"deliveryrates":[{"id":1,"comments":"good","amount_outstanding":2,"user_id":113,"created":"2018-11-27T13:23:41+0000","modified":"2018-11-27T13:23:41+0000","delivry_id":1,"rate":3.5,"user":{"username":"admin","photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png"}},{"id":2,"comments":"3","amount_outstanding":0,"user_id":113,"created":"2019-02-27T11:20:50+0000","modified":"2019-02-27T11:20:50+0000","delivry_id":1,"rate":1.5,"user":{"username":"admin","photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png"}},{"id":3,"comments":"72","amount_outstanding":0,"user_id":241,"created":"2019-02-27T11:21:25+0000","modified":"2019-02-27T11:21:25+0000","delivry_id":1,"rate":2,"user":{"username":"osda","photo":null}},{"id":4,"comments":" osama","amount_outstanding":0,"user_id":113,"created":"2019-02-27T11:26:02+0000","modified":"2019-02-27T11:26:02+0000","delivry_id":1,"rate":2,"user":{"username":"admin","photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png"}},{"id":5,"comments":"72","amount_outstanding":0,"user_id":241,"created":"2019-02-27T11:26:07+0000","modified":"2019-02-27T11:26:07+0000","delivry_id":1,"rate":5,"user":{"username":"osda","photo":null}},{"id":6,"comments":" osama","amount_outstanding":0,"user_id":113,"created":"2019-02-27T11:26:22+0000","modified":"2019-02-27T11:26:22+0000","delivry_id":1,"rate":2,"user":{"username":"admin","photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png"}},{"id":7,"comments":"ةىى","amount_outstanding":0,"user_id":241,"created":"2019-02-27T11:26:35+0000","modified":"2019-02-27T11:26:35+0000","delivry_id":1,"rate":4,"user":{"username":"osda","photo":null}},{"id":8,"comments":"تللب","amount_outstanding":0,"user_id":241,"created":"2019-02-27T11:28:58+0000","modified":"2019-02-27T11:28:58+0000","delivry_id":1,"rate":4,"user":{"username":"osda","photo":null}}]}
         */

        private int id;
        private int order_id;
        private String offer;
        private String created;
        private String modified;
        private int delivry_id;
        private DelivryBean delivry;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getOffer() {
            return offer;
        }

        public void setOffer(String offer) {
            this.offer = offer;
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

        public DelivryBean getDelivry() {
            return delivry;
        }

        public void setDelivry(DelivryBean delivry) {
            this.delivry = delivry;
        }

        public static class DelivryBean {
            /**
             * id : 1
             * name : حازم
             * user_id : 237
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
             * total : 4000
             * total_rating : [{"delivry_id":1,"stars":24,"count":8}]
             * deliveryrates : [{"id":1,"comments":"good","amount_outstanding":2,"user_id":113,"created":"2018-11-27T13:23:41+0000","modified":"2018-11-27T13:23:41+0000","delivry_id":1,"rate":3.5,"user":{"username":"admin","photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png"}},{"id":2,"comments":"3","amount_outstanding":0,"user_id":113,"created":"2019-02-27T11:20:50+0000","modified":"2019-02-27T11:20:50+0000","delivry_id":1,"rate":1.5,"user":{"username":"admin","photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png"}},{"id":3,"comments":"72","amount_outstanding":0,"user_id":241,"created":"2019-02-27T11:21:25+0000","modified":"2019-02-27T11:21:25+0000","delivry_id":1,"rate":2,"user":{"username":"osda","photo":null}},{"id":4,"comments":" osama","amount_outstanding":0,"user_id":113,"created":"2019-02-27T11:26:02+0000","modified":"2019-02-27T11:26:02+0000","delivry_id":1,"rate":2,"user":{"username":"admin","photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png"}},{"id":5,"comments":"72","amount_outstanding":0,"user_id":241,"created":"2019-02-27T11:26:07+0000","modified":"2019-02-27T11:26:07+0000","delivry_id":1,"rate":5,"user":{"username":"osda","photo":null}},{"id":6,"comments":" osama","amount_outstanding":0,"user_id":113,"created":"2019-02-27T11:26:22+0000","modified":"2019-02-27T11:26:22+0000","delivry_id":1,"rate":2,"user":{"username":"admin","photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png"}},{"id":7,"comments":"ةىى","amount_outstanding":0,"user_id":241,"created":"2019-02-27T11:26:35+0000","modified":"2019-02-27T11:26:35+0000","delivry_id":1,"rate":4,"user":{"username":"osda","photo":null}},{"id":8,"comments":"تللب","amount_outstanding":0,"user_id":241,"created":"2019-02-27T11:28:58+0000","modified":"2019-02-27T11:28:58+0000","delivry_id":1,"rate":4,"user":{"username":"osda","photo":null}}]
             */

            private int id;
            private String name;
            private int user_id;
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
            private int total;
            private List<TotalRatingBean> total_rating;
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

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
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

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<TotalRatingBean> getTotal_rating() {
                return total_rating;
            }

            public void setTotal_rating(List<TotalRatingBean> total_rating) {
                this.total_rating = total_rating;
            }

            public List<DeliveryratesBean> getDeliveryrates() {
                return deliveryrates;
            }

            public void setDeliveryrates(List<DeliveryratesBean> deliveryrates) {
                this.deliveryrates = deliveryrates;
            }

            public static class TotalRatingBean {
                /**
                 * delivry_id : 1
                 * stars : 24
                 * count : 8
                 */

                private int delivry_id;
                private int stars;
                private int count;

                public int getDelivry_id() {
                    return delivry_id;
                }

                public void setDelivry_id(int delivry_id) {
                    this.delivry_id = delivry_id;
                }

                public int getStars() {
                    return stars;
                }

                public void setStars(int stars) {
                    this.stars = stars;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }
            }

            public static class DeliveryratesBean {
                /**
                 * id : 1
                 * comments : good
                 * amount_outstanding : 2
                 * user_id : 113
                 * created : 2018-11-27T13:23:41+0000
                 * modified : 2018-11-27T13:23:41+0000
                 * delivry_id : 1
                 * rate : 3.5
                 * user : {"username":"admin","photo":"http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png"}
                 */

                private int id;
                private String comments;
                private int amount_outstanding;
                private int user_id;
                private String created;
                private String modified;
                private int delivry_id;
                private double rate;
                private UserBean user;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
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

                public double getRate() {
                    return rate;
                }

                public void setRate(double rate) {
                    this.rate = rate;
                }

                public UserBean getUser() {
                    return user;
                }

                public void setUser(UserBean user) {
                    this.user = user;
                }

                public static class UserBean {
                    /**
                     * username : admin
                     * photo : http://parashot.codesroots.com/webroot/library/1/categoryphoto/15390008811521779367.png
                     */

                    private String username;
                    private String photo;

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
                }
            }
        }
    }
}
