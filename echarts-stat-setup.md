# ECharts-Stat 库安装和使用说明

## 下载和安装 echarts-stat

由于你的项目使用的是本地资源文件（qrc:/resources/js/），你需要下载 echarts-stat 库并放到相应目录。

### 方法一：直接下载编译好的文件

1. 从 GitHub 官方仓库下载：
   - 访问：https://github.com/ecomfe/echarts-stat
   - 进入 `dist` 目录
   - 下载 `ecStat.js` 文件

2. 从 CDN 获取（可以直接下载）：
   ```
   https://cdn.jsdelivr.net/npm/echarts-stat@1.2.0/dist/ecStat.js
   ```

3. 将下载的 `ecStat.js` 文件放入你的 `resources/js/` 目录下

### 方法二：使用 npm 安装（如果支持）

```bash
npm install echarts-stat
```

然后从 `node_modules/echarts-stat/dist/ecStat.js` 复制文件到你的 resources 目录。

## 在 Qt 资源文件中配置

确保在你的 `.qrc` 文件中添加 echarts-stat 库：

```xml
<RCC>
    <qresource prefix="/resources">
        <file>js/echarts.min.js</file>
        <file>js/qwebchannel.js</file>
        <file>js/ecStat.js</file>
    </qresource>
</RCC>
```

## 如何使用

### 1. 在 HTML 中引入库

```html
<script src="qrc:/resources/js/echarts.min.js"></script>
<script src="qrc:/resources/js/qwebchannel.js"></script>
<script src="qrc:/resources/js/ecStat.js"></script>
```

### 2. 基本用法示例

#### 回归分析
```javascript
// 准备数据
const data = [[1, 2], [2, 3], [3, 4], [4, 5]];

// 线性回归
const linearRegression = ecStat.regression('linear', data);
console.log(linearRegression.expression); // 输出回归表达式

// 多项式回归
const polynomialRegression = ecStat.regression('polynomial', data, 2);

// 指数回归
const exponentialRegression = ecStat.regression('exponential', data);
```

#### 统计分析
```javascript
const dataArray = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

// 计算统计量
const mean = ecStat.statistics.mean(dataArray);
const max = ecStat.statistics.max(dataArray);
const min = ecStat.statistics.min(dataArray);
const sum = ecStat.statistics.sum(dataArray);
const variance = ecStat.statistics.sampleVariance(dataArray);
```

#### 聚类分析
```javascript
const clusterData = [
    [15.43, 28.87],
    [20.22, 17.28],
    [18.05, 22.45]
];

const clustering = ecStat.clustering.hierarchicalKMeans(clusterData, 2, false);
```

### 3. 检查库是否正确加载

```javascript
// 检查 ecStat 是否可用
if (typeof ecStat !== 'undefined') {
    console.log('✅ echarts-stat 库已成功加载');
} else {
    console.error('❌ echarts-stat 库未加载');
}
```

## 兼容性说明

- echarts-stat 需要 ECharts 4.0+ 版本
- 支持所有现代浏览器
- 可在 Node.js 环境中使用

## API 参考

### 回归分析 (Regression)
- `ecStat.regression('linear', data)` - 线性回归
- `ecStat.regression('polynomial', data, order)` - 多项式回归
- `ecStat.regression('exponential', data)` - 指数回归
- `ecStat.regression('logarithmic', data)` - 对数回归

### 统计分析 (Statistics)
- `ecStat.statistics.mean(array)` - 平均值
- `ecStat.statistics.max(array)` - 最大值
- `ecStat.statistics.min(array)` - 最小值
- `ecStat.statistics.sum(array)` - 总和
- `ecStat.statistics.median(array)` - 中位数
- `ecStat.statistics.sampleVariance(array)` - 样本方差

### 聚类分析 (Clustering)
- `ecStat.clustering.hierarchicalKMeans(data, k, stepByStep)` - 层次K均值聚类

### 直方图 (Histogram)
- `ecStat.histogram(data, method)` - 生成直方图数据

## 故障排除

1. **库未加载问题**：
   - 检查文件路径是否正确
   - 确认 .qrc 文件已更新并重新编译

2. **功能不可用**：
   - 确保在使用前检查 `typeof ecStat !== 'undefined'`
   - 查看浏览器控制台是否有错误信息

3. **数据格式错误**：
   - 回归分析需要二维数组格式：`[[x1, y1], [x2, y2], ...]`
   - 统计分析需要一维数组格式：`[value1, value2, ...]`

## 更多资源

- [官方文档](https://github.com/ecomfe/echarts-stat)
- [示例和演示](https://github.com/ecomfe/echarts-stat/tree/master/test)
- [API 完整参考](https://github.com/ecomfe/echarts-stat/blob/master/README.md)