package com.yfk.comfyui.comfyui_dao.gen.domain;

import java.util.ArrayList;
import java.util.List;

public class ImagePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ImagePOExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andImageTypeIsNull() {
            addCriterion("image_type is null");
            return (Criteria) this;
        }

        public Criteria andImageTypeIsNotNull() {
            addCriterion("image_type is not null");
            return (Criteria) this;
        }

        public Criteria andImageTypeEqualTo(int value) {
            addCriterion("image_type =", value, "imageType");
            return (Criteria) this;
        }

        public Criteria andImageTypeNotEqualTo(int value) {
            addCriterion("image_type <>", value, "imageType");
            return (Criteria) this;
        }

        public Criteria andImageTypeGreaterThan(int value) {
            addCriterion("image_type >", value, "imageType");
            return (Criteria) this;
        }

        public Criteria andImageTypeGreaterThanOrEqualTo(int value) {
            addCriterion("image_type >=", value, "imageType");
            return (Criteria) this;
        }

        public Criteria andImageTypeLessThan(int value) {
            addCriterion("image_type <", value, "imageType");
            return (Criteria) this;
        }

        public Criteria andImageTypeLessThanOrEqualTo(int value) {
            addCriterion("image_type <=", value, "imageType");
            return (Criteria) this;
        }

        public Criteria andImageTypeIn(List<Integer> values) {
            addCriterion("image_type in", values, "imageType");
            return (Criteria) this;
        }

        public Criteria andImageTypeNotIn(List<Integer> values) {
            addCriterion("image_type not in", values, "imageType");
            return (Criteria) this;
        }

        public Criteria andImageTypeBetween(int value1, int value2) {
            addCriterion("image_type between", value1, value2, "imageType");
            return (Criteria) this;
        }

        public Criteria andImageTypeNotBetween(int value1, int value2) {
            addCriterion("image_type not between", value1, value2, "imageType");
            return (Criteria) this;
        }

        public Criteria andImageNameIsNull() {
            addCriterion("image_name is null");
            return (Criteria) this;
        }

        public Criteria andImageNameIsNotNull() {
            addCriterion("image_name is not null");
            return (Criteria) this;
        }

        public Criteria andImageNameEqualTo(String value) {
            addCriterion("image_name =", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameNotEqualTo(String value) {
            addCriterion("image_name <>", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameGreaterThan(String value) {
            addCriterion("image_name >", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameGreaterThanOrEqualTo(String value) {
            addCriterion("image_name >=", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameLessThan(String value) {
            addCriterion("image_name <", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameLessThanOrEqualTo(String value) {
            addCriterion("image_name <=", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameLike(String value) {
            addCriterion("image_name like", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameNotLike(String value) {
            addCriterion("image_name not like", value, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameIn(List<String> values) {
            addCriterion("image_name in", values, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameNotIn(List<String> values) {
            addCriterion("image_name not in", values, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameBetween(String value1, String value2) {
            addCriterion("image_name between", value1, value2, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNameNotBetween(String value1, String value2) {
            addCriterion("image_name not between", value1, value2, "imageName");
            return (Criteria) this;
        }

        public Criteria andImageNewNameIsNull() {
            addCriterion("image_new_name is null");
            return (Criteria) this;
        }

        public Criteria andImageNewNameIsNotNull() {
            addCriterion("image_new_name is not null");
            return (Criteria) this;
        }

        public Criteria andImageNewNameEqualTo(String value) {
            addCriterion("image_new_name =", value, "imageNewName");
            return (Criteria) this;
        }

        public Criteria andImageNewNameNotEqualTo(String value) {
            addCriterion("image_new_name <>", value, "imageNewName");
            return (Criteria) this;
        }

        public Criteria andImageNewNameGreaterThan(String value) {
            addCriterion("image_new_name >", value, "imageNewName");
            return (Criteria) this;
        }

        public Criteria andImageNewNameGreaterThanOrEqualTo(String value) {
            addCriterion("image_new_name >=", value, "imageNewName");
            return (Criteria) this;
        }

        public Criteria andImageNewNameLessThan(String value) {
            addCriterion("image_new_name <", value, "imageNewName");
            return (Criteria) this;
        }

        public Criteria andImageNewNameLessThanOrEqualTo(String value) {
            addCriterion("image_new_name <=", value, "imageNewName");
            return (Criteria) this;
        }

        public Criteria andImageNewNameLike(String value) {
            addCriterion("image_new_name like", value, "imageNewName");
            return (Criteria) this;
        }

        public Criteria andImageNewNameNotLike(String value) {
            addCriterion("image_new_name not like", value, "imageNewName");
            return (Criteria) this;
        }

        public Criteria andImageNewNameIn(List<String> values) {
            addCriterion("image_new_name in", values, "imageNewName");
            return (Criteria) this;
        }

        public Criteria andImageNewNameNotIn(List<String> values) {
            addCriterion("image_new_name not in", values, "imageNewName");
            return (Criteria) this;
        }

        public Criteria andImageNewNameBetween(String value1, String value2) {
            addCriterion("image_new_name between", value1, value2, "imageNewName");
            return (Criteria) this;
        }

        public Criteria andImageNewNameNotBetween(String value1, String value2) {
            addCriterion("image_new_name not between", value1, value2, "imageNewName");
            return (Criteria) this;
        }

        public Criteria andImageOnlineUrlIsNull() {
            addCriterion("image_online_url is null");
            return (Criteria) this;
        }

        public Criteria andImageOnlineUrlIsNotNull() {
            addCriterion("image_online_url is not null");
            return (Criteria) this;
        }

        public Criteria andImageOnlineUrlEqualTo(String value) {
            addCriterion("image_online_url =", value, "imageOnlineUrl");
            return (Criteria) this;
        }

        public Criteria andImageOnlineUrlNotEqualTo(String value) {
            addCriterion("image_online_url <>", value, "imageOnlineUrl");
            return (Criteria) this;
        }

        public Criteria andImageOnlineUrlGreaterThan(String value) {
            addCriterion("image_online_url >", value, "imageOnlineUrl");
            return (Criteria) this;
        }

        public Criteria andImageOnlineUrlGreaterThanOrEqualTo(String value) {
            addCriterion("image_online_url >=", value, "imageOnlineUrl");
            return (Criteria) this;
        }

        public Criteria andImageOnlineUrlLessThan(String value) {
            addCriterion("image_online_url <", value, "imageOnlineUrl");
            return (Criteria) this;
        }

        public Criteria andImageOnlineUrlLessThanOrEqualTo(String value) {
            addCriterion("image_online_url <=", value, "imageOnlineUrl");
            return (Criteria) this;
        }

        public Criteria andImageOnlineUrlLike(String value) {
            addCriterion("image_online_url like", value, "imageOnlineUrl");
            return (Criteria) this;
        }

        public Criteria andImageOnlineUrlNotLike(String value) {
            addCriterion("image_online_url not like", value, "imageOnlineUrl");
            return (Criteria) this;
        }

        public Criteria andImageOnlineUrlIn(List<String> values) {
            addCriterion("image_online_url in", values, "imageOnlineUrl");
            return (Criteria) this;
        }

        public Criteria andImageOnlineUrlNotIn(List<String> values) {
            addCriterion("image_online_url not in", values, "imageOnlineUrl");
            return (Criteria) this;
        }

        public Criteria andImageOnlineUrlBetween(String value1, String value2) {
            addCriterion("image_online_url between", value1, value2, "imageOnlineUrl");
            return (Criteria) this;
        }

        public Criteria andImageOnlineUrlNotBetween(String value1, String value2) {
            addCriterion("image_online_url not between", value1, value2, "imageOnlineUrl");
            return (Criteria) this;
        }

        public Criteria andImageFlowIdIsNull() {
            addCriterion("image_flow_id is null");
            return (Criteria) this;
        }

        public Criteria andImageFlowIdIsNotNull() {
            addCriterion("image_flow_id is not null");
            return (Criteria) this;
        }

        public Criteria andImageFlowIdEqualTo(Long value) {
            addCriterion("image_flow_id =", value, "imageFlowId");
            return (Criteria) this;
        }

        public Criteria andImageFlowIdNotEqualTo(Long value) {
            addCriterion("image_flow_id <>", value, "imageFlowId");
            return (Criteria) this;
        }

        public Criteria andImageFlowIdGreaterThan(Long value) {
            addCriterion("image_flow_id >", value, "imageFlowId");
            return (Criteria) this;
        }

        public Criteria andImageFlowIdGreaterThanOrEqualTo(Long value) {
            addCriterion("image_flow_id >=", value, "imageFlowId");
            return (Criteria) this;
        }

        public Criteria andImageFlowIdLessThan(Long value) {
            addCriterion("image_flow_id <", value, "imageFlowId");
            return (Criteria) this;
        }

        public Criteria andImageFlowIdLessThanOrEqualTo(Long value) {
            addCriterion("image_flow_id <=", value, "imageFlowId");
            return (Criteria) this;
        }

        public Criteria andImageFlowIdIn(List<Long> values) {
            addCriterion("image_flow_id in", values, "imageFlowId");
            return (Criteria) this;
        }

        public Criteria andImageFlowIdNotIn(List<Long> values) {
            addCriterion("image_flow_id not in", values, "imageFlowId");
            return (Criteria) this;
        }

        public Criteria andImageFlowIdBetween(Long value1, Long value2) {
            addCriterion("image_flow_id between", value1, value2, "imageFlowId");
            return (Criteria) this;
        }

        public Criteria andImageFlowIdNotBetween(Long value1, Long value2) {
            addCriterion("image_flow_id not between", value1, value2, "imageFlowId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

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