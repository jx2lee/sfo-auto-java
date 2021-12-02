# _sfo-auto-java_
* dooray automation project with python flask -> java spring
* spring & java for learning

## _How to run_
* repo clone
* set application.yml in `src/main/resources`
  * [API document](https://helpdesk.dooray.com/share/pages/9wWo-xwiR66BO5LGshgVTg/2939987647631384419)
  ```yaml
  spring:
    profiles:
      active: {set_your_active_profile}
    jackson:
      serialization:
        FAIL_ON_EMPTY_BEANS: false
  
  server:
    port: {set_port}
  
  dooray:
    token: {set_dooray_token}
    base-url: {base_uri}
    project-id: {your_dooray_project_id}
  ```
* test & build
* run jar or script
  * `java -Dspring.profiles.active={your_active_profile) -jar /your/jar/path/sfo-auto-java-1.0.0.jar`
  * `export ROOT_DIR={this.repo} && sh scripts/start-app.sh`
    * if u stop app, `sh scripts/stop-app.sh`

---
_made by jaejun.lee_
