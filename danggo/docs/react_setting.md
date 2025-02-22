### 리액트 설치

1. node.js LTS 버전 설치 https://nodejs.org/en
2. 설치 확인
    ```
    node -v
    npm -v
    ```
3. File → Settings → Languages&Frameworks → Node.js → Node interpreter 경로 확인  
   **node.exe로 설정되어 있지 않은 경우, Add Local → Node 설치 경로의 node.exe 선택**
   ![Image](https://github.com/user-attachments/assets/cd574779-575f-4d9d-912e-b8bb10e48972)
4. React 프로젝트 생성
   ```
   cd src/main
   npx create-react-app 폴더명
   
   # 설치 완료 후 (Happy hacking! 문구 출력)
   
   cd 폴더명
   npm install
   npm run-script build
   npm start
   ```
   
***''npm' 용어가 cmdlet, 함수, 스크립트 파일 또는 실행할 수 있는 프로그램 이름으로 인식되지 않습니다.' 오류 발생 시 해결법***
* terminal이 powershell로 열리도록 연결되어 있는 상태
* 방법 1) Terminal 상단 탭 아래방향 화살표 클릭 Command Prompt 선택 ![Image](https://github.com/user-attachments/assets/35dda708-7f34-403d-9d91-aba61811b0b4)
* 방법 2) File → Settings → Tools → Terminal → Shell path → cmd 선택하여 기본 값 변경

---

### 리액트 & 스프링부트 연동
* package.json → `"proxy": "http://localhost:8080",` proxy에 스프링부트 포트 추가

---

### 리액트 & 스프링부트 함께 빌드하기
* 스프링부트 build.gradle에 아래 코드 추가 (주의! frontendDir 경로 잘 확인)
* 스프링부트 실행 후 localhost:8080 접속
```
def frontendDir = "$projectDir/src/main/front"

sourceSets {
	main {
		resources { srcDirs = ["$projectDir/src/main/resources"]
		}
	}
}

processResources { dependsOn "copyReactBuildFiles" }

task installReact(type: Exec) {
	workingDir "$frontendDir"
	inputs.dir "$frontendDir"
	group = BasePlugin.BUILD_GROUP
	if (System.getProperty('os.name').toLowerCase(Locale.ROOT).contains('windows')) {
		commandLine "npm.cmd", "audit", "fix"
		commandLine 'npm.cmd', 'install' }
	else {
		commandLine "npm", "audit", "fix" commandLine 'npm', 'install'
	}
}

task buildReact(type: Exec) {
	dependsOn "installReact"
	workingDir "$frontendDir"
	inputs.dir "$frontendDir"
	group = BasePlugin.BUILD_GROUP
	if (System.getProperty('os.name').toLowerCase(Locale.ROOT).contains('windows')) {
		commandLine "npm.cmd", "run-script", "build"
	} else {
		commandLine "npm", "run-script", "build"
	}
}

task copyReactBuildFiles(type: Copy) {
	dependsOn "buildReact"
	from "$frontendDir/build"
	into "$projectDir/src/main/resources/static"
}
```