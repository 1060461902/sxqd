package edu.zjgsu.ito.model;

import java.util.ArrayList;
import java.util.List;

public class ScoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public ScoreExample() {
        oredCriteria = new ArrayList<Criteria>();
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(Integer value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(Integer value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(Integer value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(Integer value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(Integer value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<Integer> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<Integer> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(Integer value1, Integer value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andTWeekReportIsNull() {
            addCriterion("t_week_report is null");
            return (Criteria) this;
        }

        public Criteria andTWeekReportIsNotNull() {
            addCriterion("t_week_report is not null");
            return (Criteria) this;
        }

        public Criteria andTWeekReportEqualTo(Float value) {
            addCriterion("t_week_report =", value, "tWeekReport");
            return (Criteria) this;
        }

        public Criteria andTWeekReportNotEqualTo(Float value) {
            addCriterion("t_week_report <>", value, "tWeekReport");
            return (Criteria) this;
        }

        public Criteria andTWeekReportGreaterThan(Float value) {
            addCriterion("t_week_report >", value, "tWeekReport");
            return (Criteria) this;
        }

        public Criteria andTWeekReportGreaterThanOrEqualTo(Float value) {
            addCriterion("t_week_report >=", value, "tWeekReport");
            return (Criteria) this;
        }

        public Criteria andTWeekReportLessThan(Float value) {
            addCriterion("t_week_report <", value, "tWeekReport");
            return (Criteria) this;
        }

        public Criteria andTWeekReportLessThanOrEqualTo(Float value) {
            addCriterion("t_week_report <=", value, "tWeekReport");
            return (Criteria) this;
        }

        public Criteria andTWeekReportIn(List<Float> values) {
            addCriterion("t_week_report in", values, "tWeekReport");
            return (Criteria) this;
        }

        public Criteria andTWeekReportNotIn(List<Float> values) {
            addCriterion("t_week_report not in", values, "tWeekReport");
            return (Criteria) this;
        }

        public Criteria andTWeekReportBetween(Float value1, Float value2) {
            addCriterion("t_week_report between", value1, value2, "tWeekReport");
            return (Criteria) this;
        }

        public Criteria andTWeekReportNotBetween(Float value1, Float value2) {
            addCriterion("t_week_report not between", value1, value2, "tWeekReport");
            return (Criteria) this;
        }

        public Criteria andTSummaryIsNull() {
            addCriterion("t_summary is null");
            return (Criteria) this;
        }

        public Criteria andTSummaryIsNotNull() {
            addCriterion("t_summary is not null");
            return (Criteria) this;
        }

        public Criteria andTSummaryEqualTo(Float value) {
            addCriterion("t_summary =", value, "tSummary");
            return (Criteria) this;
        }

        public Criteria andTSummaryNotEqualTo(Float value) {
            addCriterion("t_summary <>", value, "tSummary");
            return (Criteria) this;
        }

        public Criteria andTSummaryGreaterThan(Float value) {
            addCriterion("t_summary >", value, "tSummary");
            return (Criteria) this;
        }

        public Criteria andTSummaryGreaterThanOrEqualTo(Float value) {
            addCriterion("t_summary >=", value, "tSummary");
            return (Criteria) this;
        }

        public Criteria andTSummaryLessThan(Float value) {
            addCriterion("t_summary <", value, "tSummary");
            return (Criteria) this;
        }

        public Criteria andTSummaryLessThanOrEqualTo(Float value) {
            addCriterion("t_summary <=", value, "tSummary");
            return (Criteria) this;
        }

        public Criteria andTSummaryIn(List<Float> values) {
            addCriterion("t_summary in", values, "tSummary");
            return (Criteria) this;
        }

        public Criteria andTSummaryNotIn(List<Float> values) {
            addCriterion("t_summary not in", values, "tSummary");
            return (Criteria) this;
        }

        public Criteria andTSummaryBetween(Float value1, Float value2) {
            addCriterion("t_summary between", value1, value2, "tSummary");
            return (Criteria) this;
        }

        public Criteria andTSummaryNotBetween(Float value1, Float value2) {
            addCriterion("t_summary not between", value1, value2, "tSummary");
            return (Criteria) this;
        }

        public Criteria andTFinalReportIsNull() {
            addCriterion("t_final_report is null");
            return (Criteria) this;
        }

        public Criteria andTFinalReportIsNotNull() {
            addCriterion("t_final_report is not null");
            return (Criteria) this;
        }

        public Criteria andTFinalReportEqualTo(Float value) {
            addCriterion("t_final_report =", value, "tFinalReport");
            return (Criteria) this;
        }

        public Criteria andTFinalReportNotEqualTo(Float value) {
            addCriterion("t_final_report <>", value, "tFinalReport");
            return (Criteria) this;
        }

        public Criteria andTFinalReportGreaterThan(Float value) {
            addCriterion("t_final_report >", value, "tFinalReport");
            return (Criteria) this;
        }

        public Criteria andTFinalReportGreaterThanOrEqualTo(Float value) {
            addCriterion("t_final_report >=", value, "tFinalReport");
            return (Criteria) this;
        }

        public Criteria andTFinalReportLessThan(Float value) {
            addCriterion("t_final_report <", value, "tFinalReport");
            return (Criteria) this;
        }

        public Criteria andTFinalReportLessThanOrEqualTo(Float value) {
            addCriterion("t_final_report <=", value, "tFinalReport");
            return (Criteria) this;
        }

        public Criteria andTFinalReportIn(List<Float> values) {
            addCriterion("t_final_report in", values, "tFinalReport");
            return (Criteria) this;
        }

        public Criteria andTFinalReportNotIn(List<Float> values) {
            addCriterion("t_final_report not in", values, "tFinalReport");
            return (Criteria) this;
        }

        public Criteria andTFinalReportBetween(Float value1, Float value2) {
            addCriterion("t_final_report between", value1, value2, "tFinalReport");
            return (Criteria) this;
        }

        public Criteria andTFinalReportNotBetween(Float value1, Float value2) {
            addCriterion("t_final_report not between", value1, value2, "tFinalReport");
            return (Criteria) this;
        }

        public Criteria andAdditionalScoreIsNull() {
            addCriterion("additional_score is null");
            return (Criteria) this;
        }

        public Criteria andAdditionalScoreIsNotNull() {
            addCriterion("additional_score is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionalScoreEqualTo(Float value) {
            addCriterion("additional_score =", value, "additionalScore");
            return (Criteria) this;
        }

        public Criteria andAdditionalScoreNotEqualTo(Float value) {
            addCriterion("additional_score <>", value, "additionalScore");
            return (Criteria) this;
        }

        public Criteria andAdditionalScoreGreaterThan(Float value) {
            addCriterion("additional_score >", value, "additionalScore");
            return (Criteria) this;
        }

        public Criteria andAdditionalScoreGreaterThanOrEqualTo(Float value) {
            addCriterion("additional_score >=", value, "additionalScore");
            return (Criteria) this;
        }

        public Criteria andAdditionalScoreLessThan(Float value) {
            addCriterion("additional_score <", value, "additionalScore");
            return (Criteria) this;
        }

        public Criteria andAdditionalScoreLessThanOrEqualTo(Float value) {
            addCriterion("additional_score <=", value, "additionalScore");
            return (Criteria) this;
        }

        public Criteria andAdditionalScoreIn(List<Float> values) {
            addCriterion("additional_score in", values, "additionalScore");
            return (Criteria) this;
        }

        public Criteria andAdditionalScoreNotIn(List<Float> values) {
            addCriterion("additional_score not in", values, "additionalScore");
            return (Criteria) this;
        }

        public Criteria andAdditionalScoreBetween(Float value1, Float value2) {
            addCriterion("additional_score between", value1, value2, "additionalScore");
            return (Criteria) this;
        }

        public Criteria andAdditionalScoreNotBetween(Float value1, Float value2) {
            addCriterion("additional_score not between", value1, value2, "additionalScore");
            return (Criteria) this;
        }

        public Criteria andCWeekReportIsNull() {
            addCriterion("c_week_report is null");
            return (Criteria) this;
        }

        public Criteria andCWeekReportIsNotNull() {
            addCriterion("c_week_report is not null");
            return (Criteria) this;
        }

        public Criteria andCWeekReportEqualTo(Float value) {
            addCriterion("c_week_report =", value, "cWeekReport");
            return (Criteria) this;
        }

        public Criteria andCWeekReportNotEqualTo(Float value) {
            addCriterion("c_week_report <>", value, "cWeekReport");
            return (Criteria) this;
        }

        public Criteria andCWeekReportGreaterThan(Float value) {
            addCriterion("c_week_report >", value, "cWeekReport");
            return (Criteria) this;
        }

        public Criteria andCWeekReportGreaterThanOrEqualTo(Float value) {
            addCriterion("c_week_report >=", value, "cWeekReport");
            return (Criteria) this;
        }

        public Criteria andCWeekReportLessThan(Float value) {
            addCriterion("c_week_report <", value, "cWeekReport");
            return (Criteria) this;
        }

        public Criteria andCWeekReportLessThanOrEqualTo(Float value) {
            addCriterion("c_week_report <=", value, "cWeekReport");
            return (Criteria) this;
        }

        public Criteria andCWeekReportIn(List<Float> values) {
            addCriterion("c_week_report in", values, "cWeekReport");
            return (Criteria) this;
        }

        public Criteria andCWeekReportNotIn(List<Float> values) {
            addCriterion("c_week_report not in", values, "cWeekReport");
            return (Criteria) this;
        }

        public Criteria andCWeekReportBetween(Float value1, Float value2) {
            addCriterion("c_week_report between", value1, value2, "cWeekReport");
            return (Criteria) this;
        }

        public Criteria andCWeekReportNotBetween(Float value1, Float value2) {
            addCriterion("c_week_report not between", value1, value2, "cWeekReport");
            return (Criteria) this;
        }

        public Criteria andCAttendanceIsNull() {
            addCriterion("c_attendance is null");
            return (Criteria) this;
        }

        public Criteria andCAttendanceIsNotNull() {
            addCriterion("c_attendance is not null");
            return (Criteria) this;
        }

        public Criteria andCAttendanceEqualTo(Float value) {
            addCriterion("c_attendance =", value, "cAttendance");
            return (Criteria) this;
        }

        public Criteria andCAttendanceNotEqualTo(Float value) {
            addCriterion("c_attendance <>", value, "cAttendance");
            return (Criteria) this;
        }

        public Criteria andCAttendanceGreaterThan(Float value) {
            addCriterion("c_attendance >", value, "cAttendance");
            return (Criteria) this;
        }

        public Criteria andCAttendanceGreaterThanOrEqualTo(Float value) {
            addCriterion("c_attendance >=", value, "cAttendance");
            return (Criteria) this;
        }

        public Criteria andCAttendanceLessThan(Float value) {
            addCriterion("c_attendance <", value, "cAttendance");
            return (Criteria) this;
        }

        public Criteria andCAttendanceLessThanOrEqualTo(Float value) {
            addCriterion("c_attendance <=", value, "cAttendance");
            return (Criteria) this;
        }

        public Criteria andCAttendanceIn(List<Float> values) {
            addCriterion("c_attendance in", values, "cAttendance");
            return (Criteria) this;
        }

        public Criteria andCAttendanceNotIn(List<Float> values) {
            addCriterion("c_attendance not in", values, "cAttendance");
            return (Criteria) this;
        }

        public Criteria andCAttendanceBetween(Float value1, Float value2) {
            addCriterion("c_attendance between", value1, value2, "cAttendance");
            return (Criteria) this;
        }

        public Criteria andCAttendanceNotBetween(Float value1, Float value2) {
            addCriterion("c_attendance not between", value1, value2, "cAttendance");
            return (Criteria) this;
        }
    }

    /**
     */
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