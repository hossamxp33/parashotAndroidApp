package com.example.hossam.parashotApp.entities;

import java.util.List;

public class Products_in_Stories_Model {

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
         * name : iphone
         * price : 10
         * last_price : 15
         * description : iphone
         * store_id : 1
         * brand : apple
         * product_info : iphone
         * amount : 2
         * guarantee : 1
         * created : 2018-09-17T16:37:29+0000
         * modified : 2018-09-17T16:38:03+0000
         * visible : false
         * total_rating : [{"product_id":1,"user_id":1,"stars":17,"count":7}]
         * productrates : [{"id":1,"rate":3,"product_id":1,"user_id":1,"created":"2018-09-19T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"},{"id":2,"rate":2,"product_id":1,"user_id":1,"created":"2018-10-03T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"},{"id":6,"rate":1,"product_id":1,"user_id":1,"created":"2018-10-08T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"}]
         * productphotos : [{"id":1,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed2","product_id":1,"created":"2018-09-18T14:47:31+0000","modified":"2018-10-15T13:59:48+0000"},{"id":2,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed5","product_id":1,"created":"2018-09-19T17:10:00+0000","modified":"2018-09-21T19:45:41+0000"},{"id":3,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed7","product_id":1,"created":"2018-09-21T19:48:22+0000","modified":"2018-09-21T19:48:22+0000"}]
         */

        private int id;
        private String name;
        private String price;
        private String last_price;
        private String description;
        private String store_id;
        private String brand;
        private String product_info;
        private int amount;
        private int guarantee;
        private String created;
        private String modified;
        private String visible;
        private List<TotalRatingBean> total_rating;
        private List<ProductratesBean> productrates;
        private List<ProductphotosBean> productphotos;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getLast_price() {
            return last_price;
        }

        public void setLast_price(String last_price) {
            this.last_price = last_price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getProduct_info() {
            return product_info;
        }

        public void setProduct_info(String product_info) {
            this.product_info = product_info;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getGuarantee() {
            return guarantee;
        }

        public void setGuarantee(int guarantee) {
            this.guarantee = guarantee;
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

        public String getVisible() {
            return visible;
        }

        public void setVisible(String visible) {
            this.visible = visible;
        }

        public List<TotalRatingBean> getTotal_rating() {
            return total_rating;
        }

        public void setTotal_rating(List<TotalRatingBean> total_rating) {
            this.total_rating = total_rating;
        }

        public List<ProductratesBean> getProductrates() {
            return productrates;
        }

        public void setProductrates(List<ProductratesBean> productrates) {
            this.productrates = productrates;
        }

        public List<ProductphotosBean> getProductphotos() {
            return productphotos;
        }

        public void setProductphotos(List<ProductphotosBean> productphotos) {
            this.productphotos = productphotos;
        }

        public static class TotalRatingBean {
            /**
             * product_id : 1
             * user_id : 1
             * stars : 17
             * count : 7
             */

            private int product_id;
            private int user_id;
            private int stars;
            private int count;

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
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

        public static class ProductratesBean {
            /**
             * id : 1
             * rate : 3
             * product_id : 1
             * user_id : 1
             * created : 2018-09-19T09:29:00+0000
             * modified : 2018-09-18T09:32:08+0000
             */

            private int id;
            private int rate;
            private int product_id;
            private int user_id;
            private String created;
            private String modified;

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

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
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
        }

        public static class ProductphotosBean {
            /**
             * id : 1
             * photo : http://parashot.codesroots.com/library/default/35768252.jpg
             * main : ahmed2
             * product_id : 1
             * created : 2018-09-18T14:47:31+0000
             * modified : 2018-10-15T13:59:48+0000
             */

            private int id;
            private String photo;
            private String main;
            private int product_id;
            private String created;
            private String modified;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
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
        }
    }
}
