# PiFind 通用库

所有 PiFind 的web服务相关项目都要引入该项目

## Maven 引入方式

**第一步** 请在 Maven 的 setting.xml 中添加如下服务器配置

```xml
    <servers>
        <server>
          <id>pifind-maven-releases</id>
          <username>subscriber</username>
          <password>PiFind@2023$</password>
        </server>
        <server>
          <id>pifind-maven-snapshots</id>
          <username>subscriber</username>
          <password>PiFind@2023$</password>
        </server>
    </servers>
```

**第二步** 在项目中增加 pifind 仓库

```xml
    <repositories>
        <repository>
            <id>pifind-maven-releases</id>
            <url>https://pifind.mvn.pub/repository/maven-releases/</url>
        </repository>
        <repository>
            <id>pifind-maven-snapshots</id>
            <url>https://pifind.mvn.pub/repository/maven-snapshots/</url>
        </repository>
    </repositories>
```

**第三步** 在项目的 pom.xml 中加入如下依赖

```xml
    <!-- PiFind 公共仓库 -->
    <dependency>
        <groupId>io.pifind</groupId>
        <artifactId>common</artifactId>
        <version>0.0.1</version>
    </dependency>
```