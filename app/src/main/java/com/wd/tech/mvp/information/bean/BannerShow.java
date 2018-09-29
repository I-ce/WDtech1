package com.wd.tech.mvp.information.bean;

import java.util.List;

/**
 * @author BySevenGroup_zwj
 * @create 2018/9/27 20:25
 * @Description
 */
public class BannerShow {
    /**
     * result : [{"imageUrl":"https://img.huxiucdn.com/article/cover/201808/31/145637698331.jpg?imageView2/1/w/710/h/400/|imageMog","jumpUrl":"wd://information/infoId=1","rank":1},{"imageUrl":"https://img.huxiucdn.com/article/cover/201808/31/145637698331.jpg?imageView2/1/w/710/h/400/|imageMog","jumpUrl":"http://www.huxiu.com/article/260217.html","rank":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List <ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List <ResultBean> getResult() {
        return result;
    }

    public void setResult(List <ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * imageUrl : https://img.huxiucdn.com/article/cover/201808/31/145637698331.jpg?imageView2/1/w/710/h/400/|imageMog
         * jumpUrl : wd://information/infoId=1
         * rank : 1
         */

        private String imageUrl;
        private String jumpUrl;
        private int rank;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getJumpUrl() {
            return jumpUrl;
        }

        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }
    }
}
