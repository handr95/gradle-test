# Gradle study

## gradle 기본 명령어

* gradle init --type basic
  * 기본 프로젝트 구성
  * 기본 타입 basic

## Task 

* task Task명 : 사용자 정의 task 
  ```groovy
  task hello {
    println 'Gradle Hello'
  }
  ``` 

* gradle tasks : task 목록 확인

* << : task 실행 순서 제어
  ```groovy
  task run << {
      println 'running now'
  }
  
  task start{
      println 'ready'
  }
  ```

* doFirst, doLast : task 실행 순서 제어
  ```groovy
  task cellphone {
    description = 'Display calling message'
    doLast {
      println '통화하기'
    }
  
    doFirst {
      println '전화걸기'
    }
  
    doLast {
      println '전화끊기'
    }
  }
  ```

* defaultTasks Task명 : 기본으로 실행해야하는 Task 디폴트로 지정
  ```groovy
  defaultTasks 'myBasicTask'
  
  task myBasicTask << {
      println "default task"
  }
  
  task other << {
      println "other run"
  }
  ```

* description:'주석' : task 설명 추가 (gradle tasks 명령어 입력시 설명 확인 가능)
  ```groovy
  task myBasicTask(description:'디폴트 Task입니다.') << {
      println "default task"
  }
  ```
* dependsOn : 다른 Task와 연관 지어 함께 실행
  * tasks로 전체 Task를 참도하고 , 연산자로 의존관계를 설정하면 other Task 실행 후 myBasicTask가 실행
  ```groovy
  tasks.myBasicTas.dependsOn other
  
  //or
  
  task myBasicTask(description:'디폴트 Task입니다.', dependsOn:'other') << {
      println "default task"
  }
  ```

* 로깅 설정
  * Logging.level = LogLevel.레벨

레벨 | 용도 
---- | ---- 
LIFECYCCLE | 에러 메시지 출력 (기본값)
QUIET | 간결한 출력 (시스템 로그는 표시하지 않음)
INFO | QUIET보다 상세
DEBUG | 모든 메시지 출력

```groovy
task myBasicTask(description:'디폴트 Task입니다.', dependsOn:'other') << {
    logging.level = LogLevel.DEBUG
    println "default task"
}

task other <<{
    println "other run"
}
```

* task 그룹화
  ```groovy
  def myGroup = 'first'
  
  task mymemtask1(group: myGroup) << {
      println "my mem1"
  }
  
  task mymemtask2(group: myGroup) << {
      println "my mem2"
  }
  ```

# 프로젝트 초기화

* java 플러그인 추가
  * build task 실행시 의존관계가 있는 compileJava와 test Task가 실행.
  ```groovy
  apply plugins : 'java'
  ```




* gradle init –-type java-library

* 레이아웃 구성
  * sourceSet : 디렉터리 전체, java의 소스디렉터리와 테스트 디렉터리를 논리적으로 표현하는 단위
  * src/test 디렉토리에 resource 폴더 생성
  ```groovy
  task makeResourceFolder << {
    sourceSets*.java.srcDirs*.each {it.mkdirs()}
  }
  ```
  * src/test 디렉토리에 resource, java 폴더 생성
  ```groovy
  task initJavaFolder << {
      sourceSets*.java.srcDirs*.each {it.mkdirs()}
      sourceSets*.resources.srcDirs*.each {it.mkdirs()}
  }
  ```
  
* 기존 프로젝트의 디렉토리 위치가 그래들과 다른 경우, 디렉터리 지정
  ```groovy
  sourceSets {    //sourceSet 속성 하위에 디렉터리 추가
    main {
      java {
          srcDirs = ['src', 'mysrc']  //소스 디렉터리 역할을 하는 디렉터리면이 `mysrc` 라면
      }    
    }
    test {
      java {
          srcDirs = ['test', 'mytest']
      }
    }
  }
  ```
  
* gradle에서 추가한 디렉터리 인식하는지 확인
  ```groovy
  task printJavafolder << {
  	sourceSets {
  		main {
  			println "java.srcDirs = ${java.srcDirs}"
  			println "resources.srcDirs = ${resources.srcDirs}"
  		}
  	}
  }
  ```
  
* 인코딩 설정
  * 윈도우에서는 인코딩이 'MS949'로 지정되어있어 compileJava Task를 실행할 때 오류 발생(?)
  ```groovy
  //build.gradle
  compileJava.options.encoding = 'UTF-8'

  //gradle.propertis jvmargs 환경 변수 값 추가
  org.gradle.jvmargs=-Dfile.encoding=UTF-8
  ```

* compileJava에서 test 무시하고 진행
  ```groovy
  sourceSets {
    main {
      java {
        srcDirs = ['src', 'mysrc']
        exclude 'test/*'
      }
    }
  }
  ```

* build 디렉터리는 반복해서 파일이 생성되는 디렉터리이므로 이전에 생성된 파일과 중복될수 있음
  * clean task 사용시 build 디렉터리 삭제됨
  
