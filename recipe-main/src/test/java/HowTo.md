#### Integration Tests
- executing sql files
    - ensure that they are not in root application props
        - they will run for every jpa test
        - better to create directories and use @Sql to pull in what is needed
        - or use the test to create data
    - need to do saveAndFlush with repository to commit
        - its rolled back after each test
    - need to use nextval to insert id, outside of entity manager
    - showing the sql queries is helpful to debug
    
    