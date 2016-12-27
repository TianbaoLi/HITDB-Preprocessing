# HITDB-Preprocessing
Precessing step for HITDB data cleaning system by Java Swing

## 数据源格式：
- SQL数据源（以mySQL为例，选择数据源ip、port、表单）
- 单个本地文本文件（定义格式规则/用户指定分隔符/自分析分隔符）
- csv/xls/xlsx文件
- Json文件
- XML文件

## 生成格式：[protobuf](https://github.com/google/protobuf)
- 前端展示部分样例数据
- 数据储存在磁盘，带时间戳
- 加“字段修改标记”一列，类型：字符串

## 现有支持的数据类型
- 数值型（整形、浮点型、百分比）
- 字符串（字符）
- 时间类型（Date/timestamp）
- 货币金额
