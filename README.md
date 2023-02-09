### 项目说明
本项目分为四个子模块：common、logging、system、tools。
#### common模块
1. 自定义全局异常类。
2. 自定义通用返回类。
3. 工具包utils（包括http请求相关工具类、文件导入导出工具类、JwtToken工具类、分页工具类、Redis工具类、RSA工具类等）。
4. 配置包config（异常处理配置、Redis配置、RSA配置、Swagger配置等）
#### logging模块
在模块logging进行日志采集，使用注解作为切入点进行日志采集，采集操作日志以及异常日志，异常日志包含异常的详细内容，并保存到数据库中。
#### system模块
1. 整合Spring Security，放行所 有静态资源以及swagger文档、文件，另外的所有请求都需要token令牌以及权限校验。（通过@PreAuthorize注解给接口赋予访问权限）
2. 结合redis实现验证码API和用户登录API，保存用户在线状态。
3. 部门、字典管理API，用户、角色管理API，任务调度API均实现了基本的增删改查，大部分实现了分页、数据导出为.xls文件。角色管理API还实现了权限分配，任务调度API实现执行（不存在则新建任务）、暂停（取消执行）任务。
