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

