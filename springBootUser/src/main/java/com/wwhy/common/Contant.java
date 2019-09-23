package com.wwhy.common;

public class Contant {
    //一天的毫秒数
    public static final Long ONE_DAY_MILLISECOND = 86400000L;

    //图片根目录
    public static final String IMG_UPLOAD_DIRECTORY = "/photo";

    //返回成功
    public static final Integer RESULT_SUCCESS = 0;
    //返回失败
    public static final Integer RESULT_FAILED = 1;
    //未登录
    public static final Integer RESULT_UNAUTHORIZED = 2;
    //未登录
    public static final String RESULT_UNAUTHORIZED_TEXT = "当前用户登录失效，请重新登录！";
    public static final String RESULT_UNAUTHORIZED_MASSAGE = "当前用户没有权限！";

    //验证码前缀
    public static final String AUTH_CODE = "smslogin_";
    //token过期时间  10天  单位秒
    public static final Integer TOKEN_SESSION_LIVE_TIME = 864000;

    //验证码过期时间  5分钟 单位秒
    public static final Integer VERIFICATION_CODE_LIVE_TIME = 300;

    public static final byte PLAYER_IS_UNABLED = 1;

    //赛事状态
    public static final String MATCH_STATUS_SIGN = "报名中";
    public static final String MATCH_STATUS_SIGN_END = "报名结束";
    public static final String MATCH_STATUS_PLAYING = "比赛中";
    public static final String MATCH_STATUS_FINISHED = "已结束";


    public static final Byte MATCH_STATUS_ONE = 1;
    public static final Byte MATCH_STATUS_TWO = 2;
    public static final Byte MATCH_STATUS_THREE = 3;
    public static final Byte MATCH_STATUS_FOUR = 4;

    public static final String PLAYER_NO_REGISTER = "当前用户未注册！";
    public static final String PLAYER_NO_EXIST = "无效的的用户名！";
    public static final String PLAYER_PASSWORD_ERROR = "密码输入错误！";
    public static final String PLAYER_UNABLE = "当前用户不可用！";

    public static final String AUTH_SUPER_ADMIN = "superAdmin";
    public static final String AUTH_NORMAL_ADMIN = "normalAdmin";
    public static final String AUTH_PLAYER = "player";

    //参赛状态
    public static final String PLAYER_STATUS_ZHENGCHANG = "已报名，正常参赛";
    public static final String PLAYER_STATUS_QUEQING = "缺勤";
    public static final String PLAYER_STATUS_CHIDAO = "迟到";
    public static final String PLAYER_STATUS_ZAOTUI = "早退";
    public static final String PLAYER_STATUS_KONGJIANG = "未报名，正常参赛";
    public static final String PLAYER_STATUS_SHANGTUI = "伤退";

    public static final byte PLAYER_STATUS_ONE = 1;
    public static final byte PLAYER_STATUS_TWO = 2;
    public static final byte PLAYER_STATUS_THREE = 3;
    public static final byte PLAYER_STATUS_FOUR = 4;
    public static final byte PLAYER_STATUS_FIVE = 5;
    public static final byte PLAYER_STATUS_SIX = 6;

    public static final String GROUP_METHOD_SELECT = "自选分组";
    public static final String GROUP_METHOD_RANDOM = "随机分组";

    public static final byte PLAYER_SIGN_ON = 1;//已报名
    public static final byte PLAYER_SIGN_OFF = 0;//未/取消报名

    public static final String TIME_UNIT_HOUR = "小时";
    public static final String TIME_UNIT_MINUTE = "分钟";

    public static final String ACCOUNT_TYPE_IN = "入账";
    public static final String ACCOUNT_TYPE_OUT = "出账";

    public static final String ADOPT_STATUS_YES = "已审批";
    public static final String ADOPT_STATUS_NO = "未审批";

    //排序
    public static final String ORDER_BY_GOAL = "totalGoal";
}


