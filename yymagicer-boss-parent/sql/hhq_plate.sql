/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50724
Source Host           : 127.0.0.1:3306
Source Database       : hhq_plate

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-05-10 18:11:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_database_config
-- ----------------------------
DROP TABLE IF EXISTS `t_database_config`;
CREATE TABLE `t_database_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config_id` varchar(32) NOT NULL COMMENT '配置id',
  `config_name` varchar(255) NOT NULL COMMENT '配置名称',
  `driver_name` varchar(255) NOT NULL COMMENT '驱动名称',
  `url` varchar(255) NOT NULL COMMENT '数据库连接地址',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `is_delete` varchar(255) NOT NULL DEFAULT '0' COMMENT '是否删除：0-否；1-是',
  `creator` varchar(255) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人id',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(255) DEFAULT '' COMMENT '更新人',
  `update_user_id` varchar(32) DEFAULT '' COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `table_schema` varchar(255) NOT NULL COMMENT '数据库名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_database_config
-- ----------------------------
INSERT INTO `t_database_config` VALUES ('1', '643949279177609216', '本地连接', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://127.0.0.1:3306/hhq_plate?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC', 'root', 'root', '0', 'admin', '1', '2019-05-09 15:40:02', '', '', '2019-05-09 15:40:02', 'hhq_plate');

-- ----------------------------
-- Table structure for t_database_table
-- ----------------------------
DROP TABLE IF EXISTS `t_database_table`;
CREATE TABLE `t_database_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `table_id` varchar(32) NOT NULL COMMENT '主键',
  `table_name` varchar(255) NOT NULL COMMENT '表名',
  `table_content` varchar(255) DEFAULT NULL COMMENT '描述',
  `class_name` varchar(255) NOT NULL COMMENT '类名',
  `is_delete` varchar(255) NOT NULL COMMENT '是否删除：0-否；1-是',
  `creator` varchar(255) NOT NULL COMMENT '创建人',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_database_table
-- ----------------------------
INSERT INTO `t_database_table` VALUES ('1', '643962669245665280', 't_role_info', '角色表', 'RolePO', '0', 'admin', '1', '2019-05-09 07:32:39', null, null, null);
INSERT INTO `t_database_table` VALUES ('2', '643962989459804160', 't_table_column', '数据库表的列数据', 'TablePO', '0', 'admin', '1', '2019-05-09 07:33:56', null, null, null);

-- ----------------------------
-- Table structure for t_table_column
-- ----------------------------
DROP TABLE IF EXISTS `t_table_column`;
CREATE TABLE `t_table_column` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `column_id` varchar(32) NOT NULL COMMENT '主键',
  `table_name` varchar(255) NOT NULL COMMENT '表名',
  `column_name` varchar(255) NOT NULL COMMENT '字段名',
  `nullable` char(1) NOT NULL COMMENT '是否可以为空；0-可以；1-不可以',
  `data_type` varchar(255) DEFAULT NULL COMMENT '字段数据类型',
  `max_length` int(11) DEFAULT NULL COMMENT '最大长度',
  `is_key` char(1) DEFAULT NULL COMMENT '是否为主键：0-是；1-否',
  `is_auto_increment` char(1) DEFAULT NULL COMMENT '是否自增长:0-是；1-否',
  `column_comment` varchar(255) DEFAULT NULL COMMENT '字段描述',
  `property_name` varchar(255) DEFAULT NULL COMMENT '字段对应代码中属性的名称',
  `property_type` varchar(255) DEFAULT NULL COMMENT '字段对应代码中属性的名称类型',
  `show_type` char(1) DEFAULT NULL COMMENT '展示类型：1-文本（text）；2-密码框（password）；3-单选框（radio）；4-复选框（checkbox）；5-按钮（button）；6-图片（image）；7-隐藏（hidden）',
  `is_query` char(1) DEFAULT NULL COMMENT '是否是查询条件:0-是；1-否',
  `is_delete` varchar(255) NOT NULL COMMENT '是否删除：0-否；1-是',
  `creator` varchar(255) NOT NULL COMMENT '创建人',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_unique` char(1) DEFAULT NULL COMMENT '是否唯一：0-是；1-否',
  `query_type` char(1) DEFAULT NULL COMMENT '查询类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `column_id` (`column_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_table_column
-- ----------------------------
INSERT INTO `t_table_column` VALUES ('1', '1', 't_user', 'user_name', '0', 'varchar', '255', '1', '1', '名称', 'userName', 'String', '1', '1', '0', '1', '1', '2019-05-10 17:13:11', null, null, '2019-05-10 17:13:11', null, null);

-- ----------------------------
-- Table structure for t_template
-- ----------------------------
DROP TABLE IF EXISTS `t_template`;
CREATE TABLE `t_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `template_id` varchar(32) NOT NULL COMMENT '主键',
  `template_name` varchar(255) DEFAULT NULL COMMENT '模板名称',
  `template_content` text COMMENT '模板内容',
  `is_delete` varchar(255) NOT NULL COMMENT '是否删除：0-否；1-是',
  `creator` varchar(255) NOT NULL COMMENT '创建人',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `template_group` varchar(255) DEFAULT NULL COMMENT '分组',
  `file_type` varchar(255) DEFAULT NULL COMMENT '文件类型',
  `generate_file_package` varchar(255) DEFAULT NULL COMMENT '生成文件名包名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_template
-- ----------------------------
INSERT INTO `t_template` VALUES ('1', '643987600507408384', 'po', 'package ${basePackage}.po;\r\n\r\nimport com.hhq.common.base.BasePO;\r\nimport lombok.Data;\r\n/**\r\n * \r\n */\r\n@Data\r\n@Table(name=\"${tableName}\")\r\npublic class ${beanName?cap_first}PO extends BasePO{\r\n	<#list columnList as column>\r\n	/**\r\n	 * ${column.columnComment}\r\n	 */\r\n	private ${column.propertyType} ${column.propertyName?uncap_first};\r\n	</#list>\r\n	\r\n}\r\n\r\n', '0', 'admin', '1', '2019-05-10 18:08:50', null, null, '2019-05-10 18:08:50', 'layui', 'po', 'po');
INSERT INTO `t_template` VALUES ('2', '644313574210015232', 'dao', 'package ${basePackage}.mapper;\r\n\r\nimport ${basePackage}.po.${beanName?cap_first};\r\nimport com.hhq.common.base.BaseMapper;\r\nimport org.springframework.stereotype.Repository;\r\n\r\n@Repository\r\npublic interface ${beanName?cap_first}Mapper extends BaseMapper<${beanName?cap_first}> {\r\n}', '0', 'admin', '1', '2019-05-10 16:40:53', null, null, '2019-05-10 16:40:53', 'layui', 'dao', 'mapper');
INSERT INTO `t_template` VALUES ('3', '644343404787929088', 'service', 'package ${basePackage}.service;\r\n\r\nimport ${basePackage}.po.${beanName?cap_first}PO;\r\nimport com.hhq.common.base.BaseService;\r\n\r\npublic interface ${beanName?cap_first}Service extends BaseService<${beanName?cap_first}PO> {\r\n}\r\n', '0', 'admin', '1', '2019-05-10 16:45:44', null, null, '2019-05-10 16:45:44', 'layui', 'service', 'service');
INSERT INTO `t_template` VALUES ('4', '644348040068468736', 'serviceImpl', 'package ${basePackage}.service.impl;\r\nimport ${basePackage}.mapper.${beanName?cap_first}Mapper;\r\nimport ${basePackage}.po.${beanName?cap_first}PO;\r\nimport ${basePackage}.service.${beanName?cap_first}Service;\r\nimport com.hhq.common.base.AbstractService;\r\nimport com.hhq.common.constant.BaseConstant;\r\nimport org.apache.commons.lang3.StringUtils;\r\nimport org.springframework.beans.factory.annotation.Autowired;\r\nimport org.springframework.stereotype.Service;\r\nimport tk.mybatis.mapper.entity.Example;\r\n\r\nimport java.util.Date;\r\nimport java.util.List;\r\n\r\n@Service\r\npublic class ${beanName?cap_first}ServiceImpl extends AbstractService<${beanName?cap_first}PO> implements ${beanName?cap_first}Service {\r\n\r\n    @Autowired\r\n    private ${beanName?cap_first}Mapper ${beanName?uncap_first}Mapper;\r\n\r\n    @Override\r\n    public int save(${beanName?cap_first}PO ${beanName?uncap_first}PO) {\r\n        return ${beanName?uncap_first}Mapper.insertSelective(${beanName?uncap_first}PO);\r\n    }\r\n\r\n    @Override\r\n    public ${beanName?cap_first}PO get(${beanName?cap_first}PO ${beanName?uncap_first}PO) {\r\n        return ${beanName?uncap_first}Mapper.selectOne(${beanName?uncap_first}PO);\r\n    }\r\n\r\n    @Override\r\n    public int update(${beanName?cap_first}PO ${beanName?uncap_first}PO) {\r\n        return ${beanName?uncap_first}Mapper.updateByPrimaryKeySelective(${beanName?uncap_first}PO);\r\n    }\r\n\r\n    @Override\r\n    public int delete(${beanName?cap_first}PO ${beanName?uncap_first}PO) {\r\n        return ${beanName?uncap_first}Mapper.deleteByPrimaryKey(${beanName?uncap_first}PO);\r\n    }\r\n\r\n    @Override\r\n    public List<${beanName?cap_first}PO> queryList(${beanName?cap_first}PO ${beanName?uncap_first}PO) {\r\n        Example example = new Example(${beanName?cap_first}PO.class);\r\n        return ${beanName?uncap_first}Mapper.selectByExample(example);\r\n    }\r\n\r\n    @Override\r\n    public int batchDelete(List<String> ids,String userId,String userName) {\r\n        Example example = new Example(${beanName?cap_first}PO.class);\r\n        Example.Criteria criteria = example.createCriteria();\r\n        ${beanName?cap_first}PO ${beanName?uncap_first}PO = new ${beanName?cap_first}PO();\r\n        ${beanName?uncap_first}PO.setIsDelete(BaseConstant.IS_DELETE);\r\n        ${beanName?uncap_first}PO.setUpdateTime(new Date());\r\n        ${beanName?uncap_first}PO.setUpdateUserId(userId);\r\n        ${beanName?uncap_first}PO.setUpdater(userName);\r\n		<#list columnList as column>\r\n			<#if column.isKey ?? && column.isKey==\'0\'>\r\n        criteria.andIn(\"${column.propertyName}\",ids);\r\n				<#break> \r\n           </#if>\r\n		</#list>\r\n        return ${beanName?uncap_first}Mapper.updateByExampleSelective(${beanName?uncap_first}PO,example);\r\n    }\r\n}\r\n\r\n\r\n', '0', 'admin', '1', '2019-05-10 17:09:16', null, null, '2019-05-10 17:09:16', 'layui', 'serviceImpl', 'service/impl');
INSERT INTO `t_template` VALUES ('5', '644361906714316800', 'vo', 'package ${basePackage}.gateway.${beanName?lower_case}.vo;\r\n\r\nimport com.hhq.common.base.BaseVO;\r\nimport lombok.Data;\r\nimport org.apache.ibatis.annotations.Insert;\r\n\r\nimport javax.validation.constraints.NotEmpty;\r\nimport java.io.Serializable;\r\n\r\n/**\r\n *\r\n */\r\n@Data\r\npublic class ${beanName?cap_first}VO extends BaseVO implements Serializable {\r\n\r\n	<#list columnList as column>\r\n	/**\r\n	 * ${column.columnComment}\r\n	 */\r\n	<#if column.nullable?? && column.nullable==\'1\'>\r\n	@NotEmpty(groups ={Insert.class},message = \"${column.columnComment}不能为空\")\r\n	</#if>\r\n	private ${column.propertyType} ${column.propertyName?uncap_first};\r\n	</#list>\r\n}\r\n', '0', 'admin', '1', '2019-05-10 18:08:52', null, null, '2019-05-10 18:08:52', 'layui', 'vo', 'vo');
INSERT INTO `t_template` VALUES ('6', '644362046959259648', 'controller', 'package ${basePackage}.controller;\r\n\r\nimport com.github.pagehelper.PageInfo;\r\nimport ${basePackage}.po.${beanName?cap_first}PO;\r\nimport ${basePackage}.service.${beanName?cap_first}Service;\r\nimport com.hhq.common.base.PageQueryParam;\r\nimport com.hhq.common.constant.BaseConstant;\r\nimport com.hhq.common.util.IdWorkerUtil;\r\nimport com.hhq.common.util.JsonUtils;\r\nimport com.hhq.common.util.ModelMapperUtil;\r\nimport ${basePackage}.gateway.${beanName?lower_case}.vo.${beanName?cap_first}VO;\r\nimport ${basePackage}.gateway.constant.CommonConstants;\r\nimport com.hhq.user.po.UserPO;\r\nimport org.apache.ibatis.annotations.Insert;\r\nimport org.apache.shiro.SecurityUtils;\r\nimport org.springframework.beans.factory.annotation.Autowired;\r\nimport org.springframework.validation.annotation.Validated;\r\nimport org.springframework.web.bind.annotation.RequestBody;\r\nimport org.springframework.web.bind.annotation.RequestMapping;\r\nimport org.springframework.web.bind.annotation.RequestParam;\r\nimport org.springframework.web.bind.annotation.RestController;\r\n\r\nimport java.util.Date;\r\nimport java.util.List;\r\n\r\n@RestController\r\n@RequestMapping(\"/${beanName?uncap_first}\")\r\npublic class ${beanName?cap_first}Controller {\r\n\r\n    @Autowired\r\n    private ${beanName?cap_first}Service ${beanName?uncap_first}Service;\r\n\r\n    /**\r\n     * 分页查询\r\n     * @param vo\r\n     * @return\r\n     */\r\n    @RequestMapping(\"/queryListByPage\")\r\n    public String queryListByPage(@RequestBody @Validated ${beanName?cap_first}VO vo){\r\n        ${beanName?cap_first}PO ${beanName?uncap_first}PO = ModelMapperUtil.strictMap(vo, ${beanName?cap_first}PO.class);\r\n        PageQueryParam<${beanName?cap_first}PO> param  =new PageQueryParam<>();\r\n        param.setPageSize(vo.getPageSize());\r\n        param.setPageNum(vo.getPageNum());\r\n        param.setT(${beanName?uncap_first}PO);\r\n        PageInfo<${beanName?cap_first}PO> pageInfo = ${beanName?uncap_first}Service.queryListByPage(param);\r\n        return JsonUtils.getSucc(pageInfo);\r\n    }\r\n\r\n    /**\r\n     * 查询列表\r\n     * @param vo\r\n     * @return\r\n     */\r\n    @RequestMapping(\"/queryList\")\r\n    public String queryList(@RequestBody @Validated ${beanName?cap_first}VO vo){\r\n        ${beanName?cap_first}PO ${beanName?uncap_first}PO = ModelMapperUtil.strictMap(vo, ${beanName?cap_first}PO.class);\r\n        List<${beanName?cap_first}PO> dataList = ${beanName?uncap_first}Service.queryList(${beanName?uncap_first}PO);\r\n        return JsonUtils.getSucc(dataList);\r\n    }\r\n\r\n    /**\r\n     * 新增\r\n     * @param vo\r\n     * @return\r\n     */\r\n    @RequestMapping(\"/add\")\r\n    public String add(@RequestBody @Validated(value = {Insert.class}) ${beanName?cap_first}VO vo){\r\n        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();\r\n        ${beanName?cap_first}PO ${beanName?uncap_first}PO = ModelMapperUtil.strictMap(vo, ${beanName?cap_first}PO.class);\r\n		<#list columnList as column>\r\n			<#if column.isUnique ?? && column.isUnique==\'0\'>\r\n        ${beanName?cap_first}PO query = new ${beanName?cap_first}PO();\r\n		query.set${column.propertyName?cap_first}(vo.get{column.propertyName?cap_first});\r\n        ${beanName?cap_first}PO queryByUnqiue = ${beanName?uncap_first}Service.get(query);\r\n        if(null!=queryByUnqiue){\r\n         return    JsonUtils.getFail(CommonConstants.NAME_EXIST,CommonConstants.NAME_EXIST_MSG);\r\n        }\r\n			<#break> \r\n           </#if>\r\n		</#list>\r\n        \r\n        ${beanName?uncap_first}PO.setConfigId(IdWorkerUtil.getFlowIdWorkerInstance().nextId());\r\n        ${beanName?uncap_first}PO.setIsDelete(BaseConstant.IS_NOTE_DELETE);\r\n        ${beanName?uncap_first}PO.setCreateTime(new Date());\r\n        ${beanName?uncap_first}PO.setCreateUserId(user.getCreateUserId());\r\n        ${beanName?uncap_first}PO.setCreator(user.getUserName());\r\n        ${beanName?uncap_first}Service.save(${beanName?uncap_first}PO);\r\n        return JsonUtils.getSucc();\r\n    }\r\n    /**\r\n     * 更新\r\n     * @param vo\r\n     * @return\r\n     */\r\n    @RequestMapping(\"/update\")\r\n    public String update(@RequestBody @Validated(value = {Insert.class}) ${beanName?cap_first}VO vo){\r\n        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();\r\n        ${beanName?cap_first}PO ${beanName?uncap_first}PO = ModelMapperUtil.strictMap(vo, ${beanName?cap_first}PO.class);\r\n        ${beanName?uncap_first}PO.setUpdateTime(new Date());\r\n        ${beanName?uncap_first}PO.setUpdateUserId(user.getUserId());\r\n        ${beanName?uncap_first}PO.setUpdater(user.getUserName());\r\n        ${beanName?uncap_first}Service.update(${beanName?uncap_first}PO);\r\n        return JsonUtils.getSucc();\r\n    }\r\n\r\n    /**\r\n     * 批量删除\r\n     * @param ids\r\n     * @return\r\n     */\r\n    @RequestMapping(\"/delete\")\r\n    public String delete(@RequestParam(\"ids\") List<String> ids){\r\n        UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();\r\n        ${beanName?uncap_first}Service.batchDelete(ids,user.getUserId(),user.getUserName());\r\n        return JsonUtils.getSucc();\r\n    }\r\n}\r\n', '0', 'admin', '1', '2019-05-10 17:59:55', null, null, '2019-05-10 17:59:55', 'layui', 'controller', 'controller');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL COMMENT '主键',
  `user_name` varchar(255) NOT NULL COMMENT '名称',
  `user_real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `sex` varchar(255) NOT NULL COMMENT '性别：0-男；1-女',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮件',
  `head_image` varchar(255) DEFAULT NULL COMMENT '头像',
  `is_delete` varchar(255) NOT NULL COMMENT '是否删除：0-否；1-是',
  `creator` varchar(255) NOT NULL COMMENT '创建人',
  `create_user_id` varchar(32) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `salt` varchar(255) NOT NULL COMMENT '加密盐',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '111', 'admin', 'admin', '1a30c2247ce37be508c56db6f8fa3e53c59213607a7ef43e61340c987abf71c4', '0', null, null, '0', '0', '1', '1', '2019-05-09 14:09:23', '11', '1', '2019-05-09 14:09:27', '306c2cfb9c0f853a0c65112c867e1bea');
