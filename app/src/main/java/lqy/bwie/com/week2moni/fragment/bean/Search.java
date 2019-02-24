package lqy.bwie.com.week2moni.fragment.bean;

import java.util.List;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/24
 * desc:
 */
public class Search {

    /**
     * result : [{"commodityId":113,"commodityName":"闪充充电器 9V-2A快充 适用 X21/X20/X9s/X9sPlus/X9/XPlay6/Xplay5/X7/X7Plus","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/sjpj/7/1.jpg","price":85,"saleNum":0},{"commodityId":128,"commodityName":"儿童人工智能机器人玩具语音对话早教第五代学习机3-6-12岁高科技遥控教育语音聊天对话会跳舞的智能机器人","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/znsb/1/1.jpg","price":689,"saleNum":0},{"commodityId":175,"commodityName":"倍晶 苹果微软联想惠普华硕戴尔宏基笔记本电脑包13英寸内胆14手提15单肩15.6小新11保护套 粉红色手提包+同色电源小包 15.6英寸","masterPic":"http://172.17.8.100/images/small/commodity/xbsd/dnb/6/1.jpg","price":119,"saleNum":0},{"commodityId":88,"commodityName":"冬新品 梵希蔓毛呢外套女短款长袖2018冬季新款宽松英伦格子翻领斜门襟大衣女","masterPic":"http://172.17.8.100/images/small/commodity/nz/wt/3/1.jpg","price":358,"saleNum":0},{"commodityId":124,"commodityName":"台湾AC5高清4K摄像机数码DV12倍光学变焦专业家用旅游","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/zxj/4/1.jpg","price":1998,"saleNum":0},{"commodityId":171,"commodityName":"HOTBOOM2018男士双肩包休闲背包潮流学生书包多功能笔记本商务14英寸电脑包5606 时尚灰","masterPic":"http://172.17.8.100/images/small/commodity/xbsd/dnb/2/1.jpg","price":109,"saleNum":0},{"commodityId":186,"commodityName":"SWISSGEAR双肩包 防水多功能笔记本电脑包14.6英寸/15.6英寸商务背包男学生书包休闲 SA-9393III","masterPic":"http://172.17.8.100/images/small/commodity/xbsd/sjb/3/1.jpg","price":99,"saleNum":0},{"commodityId":118,"commodityName":" 新款 iPad 128G WIFI 版 平板电脑","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/yyyl/5/1.jpg","price":2988,"saleNum":0},{"commodityId":132,"commodityName":"10升不锈钢智能垃圾桶 时尚香槟金感应收纳桶 家用酒店用厨房环保桶","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/znsb/5/1.jpg","price":298,"saleNum":0},{"commodityId":179,"commodityName":"莎米特SUMMIT拉杆箱22英寸PC材质万向轮旅行箱行李箱PC154T4A可扩容","masterPic":"http://172.17.8.100/images/small/commodity/xbsd/lgx/3/1.jpg","price":199,"saleNum":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 113
         * commodityName : 闪充充电器 9V-2A快充 适用 X21/X20/X9s/X9sPlus/X9/XPlay6/Xplay5/X7/X7Plus
         * masterPic : http://172.17.8.100/images/small/commodity/sjsm/sjpj/7/1.jpg
         * price : 85
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int saleNum;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
