# Arquillian + Junit + EclipseLink + PostgresSql

Exemplo de implementação de testes unitários com o [Arquillian](http://arquillian.org/), [Junit4](https://junit.org/junit4/), [EclipseLink](https://www.eclipse.org/eclipselink/) e [PostgresSQL](https://www.postgresql.org/).

## Dependências para os testes

```
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.13</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.jboss.arquillian.junit</groupId>
  <artifactId>arquillian-junit-container</artifactId>
</dependency>
<dependency>
  <groupId>org.wildfly.arquillian</groupId>
  <artifactId>wildfly-arquillian-container-managed</artifactId>
  <version>2.2.0.Final</version>
</dependency>
<dependency>
  <groupId>org.jboss.shrinkwrap.resolver</groupId>
  <artifactId>shrinkwrap-resolver-bom</artifactId>
  <version>3.1.4</version>
  <type>pom</type>
</dependency>
```

## Configurações
```

```
