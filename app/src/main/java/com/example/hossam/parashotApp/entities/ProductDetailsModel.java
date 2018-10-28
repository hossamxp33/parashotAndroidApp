package com.example.hossam.parashotApp.entities;

import java.util.List;

public class ProductDetailsModel {


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
         * subcat_id :
         * category_id :
         * price : 10
         * last_price : 15
         * description : iphone is a line of smartphones designed and marketed by Apple Inc
         * store_id : 50
         * brand : apple
         * product_info : <!DOCTYPE html>
         <html>
         <head>


         </head>
         <body>
         <ul>
         <li>دقه الكاميرا:5 ميجابكسل</li>
         <li>رام: 1 جيجابايت</li>
         <li>كاميرا ثانيه:5 ميجابكسل</li>
         <li>بنيه الشبكات:4جي ال تي اي</li>
         <li>دقه الكاميرا:5 ميجابكسل</li>
         <li>رام: 1 جيجابايت</li>
         <li>كاميرا ثانيه:5 ميجابكسل</li>
         <li>بنيه الشبكات:4جي ال تي اي</li>
         <li>دقه الكاميرا:5 ميجابكسل</li>
         <li>رام: 1 جيجابايت</li>
         <li>كاميرا ثانيه:5 ميجابكسل</li>
         <li>بنيه الشبكات:4جي ال تي اي</li>
         </ul>
         </body>
         </html>
         * amount : 2
         * guarantee : 1
         * created : 2018-09-17T16:37:29+0000
         * modified : 2018-09-17T16:38:03+0000
         * visible : false
         * store : {"id":50,"name":"ahmed12345"}
         * productrates : [{"id":1,"rate":3,"product_id":1,"user_id":1,"created":"2018-09-19T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"},{"id":2,"rate":2,"product_id":1,"user_id":1,"created":"2018-10-03T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"},{"id":6,"rate":1,"product_id":1,"user_id":1,"created":"2018-10-08T09:29:00+0000","modified":"2018-09-18T09:32:08+0000"}]
         * productsizes : [{"id":1,"product_id":1,"size":"400","created":"2018-09-18T11:06:14+0000","modified":"2018-09-18T11:06:32+0000"}]
         * productcolors : [{"id":1,"product_id":1,"color_hex":"#000000","red":"255","blue":"255","green":"255","created":"2018-09-18T13:08:38+0000","modified":"2018-09-18T13:26:03+0000"},{"id":2,"product_id":1,"color_hex":"#4286f4","red":"66","blue":"134","green":"244","created":"2018-09-18T13:08:38+0000","modified":"2018-09-18T13:26:03+0000"},{"id":3,"product_id":1,"color_hex":"#111214","red":"17","blue":"18","green":"20","created":"2018-09-18T13:08:38+0000","modified":"2018-09-18T13:26:03+0000"},{"id":4,"product_id":1,"color_hex":"#1058cc","red":"16","blue":"88","green":"204","created":"2018-09-18T13:08:38+0000","modified":"2018-09-18T13:26:03+0000"}]
         * productphotos : [{"id":1,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed2","product_id":1,"created":"2018-09-18T14:47:31+0000","modified":"2018-10-15T13:59:48+0000"},{"id":2,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed5","product_id":1,"created":"2018-09-19T17:10:00+0000","modified":"2018-09-21T19:45:41+0000"},{"id":3,"photo":"http://parashot.codesroots.com/library/default/35768252.jpg","main":"ahmed7","product_id":1,"created":"2018-09-21T19:48:22+0000","modified":"2018-09-21T19:48:22+0000"}]
         * total_rating : [{"product_id":1,"stars":6,"count":3}]
         */

        private int id;
        private String name;
        private String subcat_id;
        private String category_id;
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
        private StoreBean store;
        private List<ProductratesBean> productrates;
        private List<ProductsizesBean> productsizes;
        private List<ProductcolorsBean> productcolors;
        private List<ProductphotosBean> productphotos;
        private List<TotalRatingBean> total_rating;

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

        public String getSubcat_id() {
            return subcat_id;
        }

        public void setSubcat_id(String subcat_id) {
            this.subcat_id = subcat_id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
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

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public List<ProductratesBean> getProductrates() {
            return productrates;
        }

        public void setProductrates(List<ProductratesBean> productrates) {
            this.productrates = productrates;
        }

        public List<ProductsizesBean> getProductsizes() {
            return productsizes;
        }

        public void setProductsizes(List<ProductsizesBean> productsizes) {
            this.productsizes = productsizes;
        }

        public List<ProductcolorsBean> getProductcolors() {
            return productcolors;
        }

        public void setProductcolors(List<ProductcolorsBean> productcolors) {
            this.productcolors = productcolors;
        }

        public List<ProductphotosBean> getProductphotos() {
            return productphotos;
        }

        public void setProductphotos(List<ProductphotosBean> productphotos) {
            this.productphotos = productphotos;
        }

        public List<TotalRatingBean> getTotal_rating() {
            return total_rating;
        }

        public void setTotal_rating(List<TotalRatingBean> total_rating) {
            this.total_rating = total_rating;
        }

        public static class StoreBean {
            /**
             * id : 50
             * name : ahmed12345
             */

            private int id;
            private String name;

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

        public static class ProductsizesBean {
            /**
             * id : 1
             * product_id : 1
             * size : 400
             * created : 2018-09-18T11:06:14+0000
             * modified : 2018-09-18T11:06:32+0000
             */

            private int id;
            private int product_id;
            private String size;
            private String created;
            private String modified;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
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

        public static class ProductcolorsBean {
            /**
             * id : 1
             * product_id : 1
             * color_hex : #000000
             * red : 255
             * blue : 255
             * green : 255
             * created : 2018-09-18T13:08:38+0000
             * modified : 2018-09-18T13:26:03+0000
             */

            private int id;
            private int product_id;
            private String color_hex;
            private String red;
            private String blue;
            private String green;
            private String created;
            private String modified;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public String getColor_hex() {
                return color_hex;
            }

            public void setColor_hex(String color_hex) {
                this.color_hex = color_hex;
            }

            public String getRed() {
                return red;
            }

            public void setRed(String red) {
                this.red = red;
            }

            public String getBlue() {
                return blue;
            }

            public void setBlue(String blue) {
                this.blue = blue;
            }

            public String getGreen() {
                return green;
            }

            public void setGreen(String green) {
                this.green = green;
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

        public static class TotalRatingBean {
            /**
             * product_id : 1
             * stars : 6
             * count : 3
             */

            private int product_id;
            private int stars;
            private int count;

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
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
    }
}
