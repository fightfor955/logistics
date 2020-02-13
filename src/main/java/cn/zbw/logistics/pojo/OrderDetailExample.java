package cn.zbw.logistics.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    public OrderDetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOrderDetailIdIsNull() {
            addCriterion("order_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdIsNotNull() {
            addCriterion("order_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdEqualTo(Long value) {
            addCriterion("order_detail_id =", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdNotEqualTo(Long value) {
            addCriterion("order_detail_id <>", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdGreaterThan(Long value) {
            addCriterion("order_detail_id >", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_detail_id >=", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdLessThan(Long value) {
            addCriterion("order_detail_id <", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdLessThanOrEqualTo(Long value) {
            addCriterion("order_detail_id <=", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdIn(List<Long> values) {
            addCriterion("order_detail_id in", values, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdNotIn(List<Long> values) {
            addCriterion("order_detail_id not in", values, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdBetween(Long value1, Long value2) {
            addCriterion("order_detail_id between", value1, value2, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdNotBetween(Long value1, Long value2) {
            addCriterion("order_detail_id not between", value1, value2, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNull() {
            addCriterion("goods_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNotNull() {
            addCriterion("goods_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameEqualTo(String value) {
            addCriterion("goods_name =", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotEqualTo(String value) {
            addCriterion("goods_name <>", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThan(String value) {
            addCriterion("goods_name >", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("goods_name >=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThan(String value) {
            addCriterion("goods_name <", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThanOrEqualTo(String value) {
            addCriterion("goods_name <=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLike(String value) {
            addCriterion("goods_name like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotLike(String value) {
            addCriterion("goods_name not like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIn(List<String> values) {
            addCriterion("goods_name in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotIn(List<String> values) {
            addCriterion("goods_name not in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameBetween(String value1, String value2) {
            addCriterion("goods_name between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotBetween(String value1, String value2) {
            addCriterion("goods_name not between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberIsNull() {
            addCriterion("goods_number is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberIsNotNull() {
            addCriterion("goods_number is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberEqualTo(Integer value) {
            addCriterion("goods_number =", value, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberNotEqualTo(Integer value) {
            addCriterion("goods_number <>", value, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberGreaterThan(Integer value) {
            addCriterion("goods_number >", value, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_number >=", value, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberLessThan(Integer value) {
            addCriterion("goods_number <", value, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberLessThanOrEqualTo(Integer value) {
            addCriterion("goods_number <=", value, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberIn(List<Integer> values) {
            addCriterion("goods_number in", values, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberNotIn(List<Integer> values) {
            addCriterion("goods_number not in", values, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberBetween(Integer value1, Integer value2) {
            addCriterion("goods_number between", value1, value2, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_number not between", value1, value2, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitIsNull() {
            addCriterion("goods_unit is null");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitIsNotNull() {
            addCriterion("goods_unit is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitEqualTo(Long value) {
            addCriterion("goods_unit =", value, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitNotEqualTo(Long value) {
            addCriterion("goods_unit <>", value, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitGreaterThan(Long value) {
            addCriterion("goods_unit >", value, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_unit >=", value, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitLessThan(Long value) {
            addCriterion("goods_unit <", value, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitLessThanOrEqualTo(Long value) {
            addCriterion("goods_unit <=", value, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitIn(List<Long> values) {
            addCriterion("goods_unit in", values, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitNotIn(List<Long> values) {
            addCriterion("goods_unit not in", values, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitBetween(Long value1, Long value2) {
            addCriterion("goods_unit between", value1, value2, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitNotBetween(Long value1, Long value2) {
            addCriterion("goods_unit not between", value1, value2, "goodsUnit");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitPriceIsNull() {
            addCriterion("goods_unit_price is null");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitPriceIsNotNull() {
            addCriterion("goods_unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitPriceEqualTo(Long value) {
            addCriterion("goods_unit_price =", value, "goodsUnitPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitPriceNotEqualTo(Long value) {
            addCriterion("goods_unit_price <>", value, "goodsUnitPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitPriceGreaterThan(Long value) {
            addCriterion("goods_unit_price >", value, "goodsUnitPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_unit_price >=", value, "goodsUnitPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitPriceLessThan(Long value) {
            addCriterion("goods_unit_price <", value, "goodsUnitPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitPriceLessThanOrEqualTo(Long value) {
            addCriterion("goods_unit_price <=", value, "goodsUnitPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitPriceIn(List<Long> values) {
            addCriterion("goods_unit_price in", values, "goodsUnitPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitPriceNotIn(List<Long> values) {
            addCriterion("goods_unit_price not in", values, "goodsUnitPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitPriceBetween(Long value1, Long value2) {
            addCriterion("goods_unit_price between", value1, value2, "goodsUnitPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsUnitPriceNotBetween(Long value1, Long value2) {
            addCriterion("goods_unit_price not between", value1, value2, "goodsUnitPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalIsNull() {
            addCriterion("goods_total is null");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalIsNotNull() {
            addCriterion("goods_total is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalEqualTo(Long value) {
            addCriterion("goods_total =", value, "goodsTotal");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalNotEqualTo(Long value) {
            addCriterion("goods_total <>", value, "goodsTotal");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalGreaterThan(Long value) {
            addCriterion("goods_total >", value, "goodsTotal");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_total >=", value, "goodsTotal");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalLessThan(Long value) {
            addCriterion("goods_total <", value, "goodsTotal");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalLessThanOrEqualTo(Long value) {
            addCriterion("goods_total <=", value, "goodsTotal");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalIn(List<Long> values) {
            addCriterion("goods_total in", values, "goodsTotal");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalNotIn(List<Long> values) {
            addCriterion("goods_total not in", values, "goodsTotal");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalBetween(Long value1, Long value2) {
            addCriterion("goods_total between", value1, value2, "goodsTotal");
            return (Criteria) this;
        }

        public Criteria andGoodsTotalNotBetween(Long value1, Long value2) {
            addCriterion("goods_total not between", value1, value2, "goodsTotal");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkIsNull() {
            addCriterion("goods_remark is null");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkIsNotNull() {
            addCriterion("goods_remark is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkEqualTo(String value) {
            addCriterion("goods_remark =", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkNotEqualTo(String value) {
            addCriterion("goods_remark <>", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkGreaterThan(String value) {
            addCriterion("goods_remark >", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("goods_remark >=", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkLessThan(String value) {
            addCriterion("goods_remark <", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkLessThanOrEqualTo(String value) {
            addCriterion("goods_remark <=", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkLike(String value) {
            addCriterion("goods_remark like", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkNotLike(String value) {
            addCriterion("goods_remark not like", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkIn(List<String> values) {
            addCriterion("goods_remark in", values, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkNotIn(List<String> values) {
            addCriterion("goods_remark not in", values, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkBetween(String value1, String value2) {
            addCriterion("goods_remark between", value1, value2, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkNotBetween(String value1, String value2) {
            addCriterion("goods_remark not between", value1, value2, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsWidthIsNull() {
            addCriterion("goods_width is null");
            return (Criteria) this;
        }

        public Criteria andGoodsWidthIsNotNull() {
            addCriterion("goods_width is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsWidthEqualTo(Double value) {
            addCriterion("goods_width =", value, "goodsWidth");
            return (Criteria) this;
        }

        public Criteria andGoodsWidthNotEqualTo(Double value) {
            addCriterion("goods_width <>", value, "goodsWidth");
            return (Criteria) this;
        }

        public Criteria andGoodsWidthGreaterThan(Double value) {
            addCriterion("goods_width >", value, "goodsWidth");
            return (Criteria) this;
        }

        public Criteria andGoodsWidthGreaterThanOrEqualTo(Double value) {
            addCriterion("goods_width >=", value, "goodsWidth");
            return (Criteria) this;
        }

        public Criteria andGoodsWidthLessThan(Double value) {
            addCriterion("goods_width <", value, "goodsWidth");
            return (Criteria) this;
        }

        public Criteria andGoodsWidthLessThanOrEqualTo(Double value) {
            addCriterion("goods_width <=", value, "goodsWidth");
            return (Criteria) this;
        }

        public Criteria andGoodsWidthIn(List<Double> values) {
            addCriterion("goods_width in", values, "goodsWidth");
            return (Criteria) this;
        }

        public Criteria andGoodsWidthNotIn(List<Double> values) {
            addCriterion("goods_width not in", values, "goodsWidth");
            return (Criteria) this;
        }

        public Criteria andGoodsWidthBetween(Double value1, Double value2) {
            addCriterion("goods_width between", value1, value2, "goodsWidth");
            return (Criteria) this;
        }

        public Criteria andGoodsWidthNotBetween(Double value1, Double value2) {
            addCriterion("goods_width not between", value1, value2, "goodsWidth");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeIsNull() {
            addCriterion("goods_volume is null");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeIsNotNull() {
            addCriterion("goods_volume is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeEqualTo(Double value) {
            addCriterion("goods_volume =", value, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeNotEqualTo(Double value) {
            addCriterion("goods_volume <>", value, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeGreaterThan(Double value) {
            addCriterion("goods_volume >", value, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeGreaterThanOrEqualTo(Double value) {
            addCriterion("goods_volume >=", value, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeLessThan(Double value) {
            addCriterion("goods_volume <", value, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeLessThanOrEqualTo(Double value) {
            addCriterion("goods_volume <=", value, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeIn(List<Double> values) {
            addCriterion("goods_volume in", values, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeNotIn(List<Double> values) {
            addCriterion("goods_volume not in", values, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeBetween(Double value1, Double value2) {
            addCriterion("goods_volume between", value1, value2, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsVolumeNotBetween(Double value1, Double value2) {
            addCriterion("goods_volume not between", value1, value2, "goodsVolume");
            return (Criteria) this;
        }

        public Criteria andGoodsHeavyIsNull() {
            addCriterion("goods_heavy is null");
            return (Criteria) this;
        }

        public Criteria andGoodsHeavyIsNotNull() {
            addCriterion("goods_heavy is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsHeavyEqualTo(Double value) {
            addCriterion("goods_heavy =", value, "goodsHeavy");
            return (Criteria) this;
        }

        public Criteria andGoodsHeavyNotEqualTo(Double value) {
            addCriterion("goods_heavy <>", value, "goodsHeavy");
            return (Criteria) this;
        }

        public Criteria andGoodsHeavyGreaterThan(Double value) {
            addCriterion("goods_heavy >", value, "goodsHeavy");
            return (Criteria) this;
        }

        public Criteria andGoodsHeavyGreaterThanOrEqualTo(Double value) {
            addCriterion("goods_heavy >=", value, "goodsHeavy");
            return (Criteria) this;
        }

        public Criteria andGoodsHeavyLessThan(Double value) {
            addCriterion("goods_heavy <", value, "goodsHeavy");
            return (Criteria) this;
        }

        public Criteria andGoodsHeavyLessThanOrEqualTo(Double value) {
            addCriterion("goods_heavy <=", value, "goodsHeavy");
            return (Criteria) this;
        }

        public Criteria andGoodsHeavyIn(List<Double> values) {
            addCriterion("goods_heavy in", values, "goodsHeavy");
            return (Criteria) this;
        }

        public Criteria andGoodsHeavyNotIn(List<Double> values) {
            addCriterion("goods_heavy not in", values, "goodsHeavy");
            return (Criteria) this;
        }

        public Criteria andGoodsHeavyBetween(Double value1, Double value2) {
            addCriterion("goods_heavy between", value1, value2, "goodsHeavy");
            return (Criteria) this;
        }

        public Criteria andGoodsHeavyNotBetween(Double value1, Double value2) {
            addCriterion("goods_heavy not between", value1, value2, "goodsHeavy");
            return (Criteria) this;
        }

        public Criteria andGoodDeliveryPriceIsNull() {
            addCriterion("good_delivery_price is null");
            return (Criteria) this;
        }

        public Criteria andGoodDeliveryPriceIsNotNull() {
            addCriterion("good_delivery_price is not null");
            return (Criteria) this;
        }

        public Criteria andGoodDeliveryPriceEqualTo(BigDecimal value) {
            addCriterion("good_delivery_price =", value, "goodDeliveryPrice");
            return (Criteria) this;
        }

        public Criteria andGoodDeliveryPriceNotEqualTo(BigDecimal value) {
            addCriterion("good_delivery_price <>", value, "goodDeliveryPrice");
            return (Criteria) this;
        }

        public Criteria andGoodDeliveryPriceGreaterThan(BigDecimal value) {
            addCriterion("good_delivery_price >", value, "goodDeliveryPrice");
            return (Criteria) this;
        }

        public Criteria andGoodDeliveryPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("good_delivery_price >=", value, "goodDeliveryPrice");
            return (Criteria) this;
        }

        public Criteria andGoodDeliveryPriceLessThan(BigDecimal value) {
            addCriterion("good_delivery_price <", value, "goodDeliveryPrice");
            return (Criteria) this;
        }

        public Criteria andGoodDeliveryPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("good_delivery_price <=", value, "goodDeliveryPrice");
            return (Criteria) this;
        }

        public Criteria andGoodDeliveryPriceIn(List<BigDecimal> values) {
            addCriterion("good_delivery_price in", values, "goodDeliveryPrice");
            return (Criteria) this;
        }

        public Criteria andGoodDeliveryPriceNotIn(List<BigDecimal> values) {
            addCriterion("good_delivery_price not in", values, "goodDeliveryPrice");
            return (Criteria) this;
        }

        public Criteria andGoodDeliveryPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("good_delivery_price between", value1, value2, "goodDeliveryPrice");
            return (Criteria) this;
        }

        public Criteria andGoodDeliveryPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("good_delivery_price not between", value1, value2, "goodDeliveryPrice");
            return (Criteria) this;
        }

        public Criteria andGoodLengthIsNull() {
            addCriterion("good_length is null");
            return (Criteria) this;
        }

        public Criteria andGoodLengthIsNotNull() {
            addCriterion("good_length is not null");
            return (Criteria) this;
        }

        public Criteria andGoodLengthEqualTo(Double value) {
            addCriterion("good_length =", value, "goodLength");
            return (Criteria) this;
        }

        public Criteria andGoodLengthNotEqualTo(Double value) {
            addCriterion("good_length <>", value, "goodLength");
            return (Criteria) this;
        }

        public Criteria andGoodLengthGreaterThan(Double value) {
            addCriterion("good_length >", value, "goodLength");
            return (Criteria) this;
        }

        public Criteria andGoodLengthGreaterThanOrEqualTo(Double value) {
            addCriterion("good_length >=", value, "goodLength");
            return (Criteria) this;
        }

        public Criteria andGoodLengthLessThan(Double value) {
            addCriterion("good_length <", value, "goodLength");
            return (Criteria) this;
        }

        public Criteria andGoodLengthLessThanOrEqualTo(Double value) {
            addCriterion("good_length <=", value, "goodLength");
            return (Criteria) this;
        }

        public Criteria andGoodLengthIn(List<Double> values) {
            addCriterion("good_length in", values, "goodLength");
            return (Criteria) this;
        }

        public Criteria andGoodLengthNotIn(List<Double> values) {
            addCriterion("good_length not in", values, "goodLength");
            return (Criteria) this;
        }

        public Criteria andGoodLengthBetween(Double value1, Double value2) {
            addCriterion("good_length between", value1, value2, "goodLength");
            return (Criteria) this;
        }

        public Criteria andGoodLengthNotBetween(Double value1, Double value2) {
            addCriterion("good_length not between", value1, value2, "goodLength");
            return (Criteria) this;
        }

        public Criteria andGoodHeightIsNull() {
            addCriterion("good_height is null");
            return (Criteria) this;
        }

        public Criteria andGoodHeightIsNotNull() {
            addCriterion("good_height is not null");
            return (Criteria) this;
        }

        public Criteria andGoodHeightEqualTo(Double value) {
            addCriterion("good_height =", value, "goodHeight");
            return (Criteria) this;
        }

        public Criteria andGoodHeightNotEqualTo(Double value) {
            addCriterion("good_height <>", value, "goodHeight");
            return (Criteria) this;
        }

        public Criteria andGoodHeightGreaterThan(Double value) {
            addCriterion("good_height >", value, "goodHeight");
            return (Criteria) this;
        }

        public Criteria andGoodHeightGreaterThanOrEqualTo(Double value) {
            addCriterion("good_height >=", value, "goodHeight");
            return (Criteria) this;
        }

        public Criteria andGoodHeightLessThan(Double value) {
            addCriterion("good_height <", value, "goodHeight");
            return (Criteria) this;
        }

        public Criteria andGoodHeightLessThanOrEqualTo(Double value) {
            addCriterion("good_height <=", value, "goodHeight");
            return (Criteria) this;
        }

        public Criteria andGoodHeightIn(List<Double> values) {
            addCriterion("good_height in", values, "goodHeight");
            return (Criteria) this;
        }

        public Criteria andGoodHeightNotIn(List<Double> values) {
            addCriterion("good_height not in", values, "goodHeight");
            return (Criteria) this;
        }

        public Criteria andGoodHeightBetween(Double value1, Double value2) {
            addCriterion("good_height between", value1, value2, "goodHeight");
            return (Criteria) this;
        }

        public Criteria andGoodHeightNotBetween(Double value1, Double value2) {
            addCriterion("good_height not between", value1, value2, "goodHeight");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_order_detail
     *
     * @mbg.generated do_not_delete_during_merge Tue Oct 15 21:52:23 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_order_detail
     *
     * @mbg.generated Tue Oct 15 21:52:23 CST 2019
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}