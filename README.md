# 进度表

> 序号对应<结对编程项目需求>的功能序号
>
> 24.9.19 16:25 By Roeland

## 1.图形化界面

已完成：

- 登录界面(login.html)
- 选题界面(quiz.html)

未完成：

- 注册界面(register.html)
  - 功能未完善(具体见2)
- 出题界面
- 分数界面
- (可能需要的)登录后选项界面(用于登录后重置密码+进入选题做题的过渡?)

## 2.注册功能

已完成：

- 输入邮箱、密码即可注册

未完成：

- 注册码注册

## 3.设置密码

已完成：

- 最基础的注册输入密码

未完成：

- 输入两次密码匹配后设置成功
- 密码6-10位，含大小写+数字
- 登录状态下修改密码(这个登录状态可能还需要一个页面)

## 4.选择界面及功能

已完成

## 5.出题界面及功能

未完成。

出题界面的暂定的接口是"/generate-quiz"

详见`QuizController`第36行

```java
@PostMapping("/generate-quiz")
```

`ElementaryQuestionGenerator`、`MiddleQuestionGenerator`、`HighQuestionGenerator`的具体功能是空的，需要自行填充

我觉得可以使用个人项目的方法

## 6.分数界面及其功能

未完成。

分数界面的接口暂定为"/submit-answers"

详见`QuizController`第50行

```java
@PostMapping("/submit-answers")
```

分数界面的完成依托于出题界面及其功能的实现

## 7.退出/继续做题

5，6做完，7能做

我觉得7的功能可以集成到分数界面

## 8.其他

**页面权限设置**：

因`org.springframework.security`包写死了生成认证用户的过程，而我们采用自定义的登录URL(/login)，所以暂时没法让通过我们自定义注册的用户获得spring的认证权限，因此无法设置页面权限，即：

```java
.requestMatchers("/login", "/register", "/error", "/quiz").permitAll() // 允许未认证用户访问登录、注册和错误页面
// .requestMatchers("/quiz").authenticated() // 允许认证用户访问选题、答题页面
```

如果这个权限问题无法解决，或暂时无法解决，可以将新建的页面放在`permitAll`中，即：
```java
.requestMatchers("/login", "/register", "/error", "/quiz", "/xxxxx").permitAll() // 允许未认证用户访问登录、注册和错误等页面
```

个人认为，该问题的优先度较低，不是要求中明确需要的，可以在前面的功能都完成后再解决。

**页面优化设置**：

这个也可以先不急。或者在生成时让大模型自己先生成一个，后期再统一。

**Or More ...**