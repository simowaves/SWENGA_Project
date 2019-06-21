# WTF- WhatToFood
WTF is an recipe distribution platform application and was developed during the study course Software Engineering Advanced at FH JOANNEUM (UAS)
in the Bachelor's degree Informationsmanagement (IMA17). The project was supported by *DI (FH) Johann Blauensteiner* and *DI (FH) Stefan Krausler-Baumann*.

Team Members:
- Simone Andreetto
- Lukas Bichler
- Tim Braunauer
- Julian Delazer

Workload distribution:
- Simone Andreetto: Frontend; everything picture related, admin/login/navigation pages, design overhaul for Bichler's pages
- Lukas Bichler: Frontend; user and partly recipe pages, raw version of landing page
- Tim Braunauer: Backend; data model design, security, everything database related
- Julian Delazer: Backend; test data, everything backend-related in connection with pages from Lukas Bichler


## Setup Guide
1. Download project source from [here](https://github.com/simowaves/SWENGA_Project.git)
2. Create ``New dynamic Web project`` eclipse project, convert to maven project and import sources  
3. Setup ``db.properties`` in folder ``src`` (db-connection-information) based on [this](https://gist.github.com/MasterofBisaster/40265a21d2c08060caa1c07f5b9c8a33).
4. Change jpa-properties in dispatcher-servlet.xml to required attributes (``validate``, ``update``, or ``create-drop``)
5. Setup your eclipse project (Server, Runtime, ...)
6. Publish project to Tomcat and start Tomcat (9.0)
7. Open Web application [here](http://localhost:8080/SwengaProjectWtf/)
8. If there is a new database behind (or started via create-drop) , this site  [here](http://localhost:8080/SwengaProjectWtf/fillTestData)  ``/fillTestData`` must be called to create the test data.
9. To Login, click the Login Button in the upper right corner and enter one of the following credentials:  ``admin/password ``,  ``user/password ``
(Note: The website can be used to some extent as a guest without logging in)
10. You are now using WTF - WhatToFood
