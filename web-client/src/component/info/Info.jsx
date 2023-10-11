import React from "react";
import style from "./Info.module.css";

const Info= () =>{
    return(
        <div className={style.info}>
            <h3>Task Managemer Project</h3>
            <hr/>
            <p>
                The main purpose of this project is to create convenient backend system
                of task management, providing the opportunity to work not only with tasks
                as the main essence of the system, but also to perform a number of related
                processes (user registration, access control, reporting, etc.)
            </p>
            <hr/>
            <p>
                The following technologies/frameworks were used in this project:
            </p>
            <hr/>
            <ul>
                <li>Spring:
                <ul>
                    <li>Spring Boot</li>
                    <li>Spring JPA</li>
                    <li>Spring MVC</li>
                    <li>Spring Security</li>
                    <li>Spring Email</li>
                </ul>
                </li>
                <li>PostgreSQL</li>
                <li>Hibernate</li>
                <li>Nginx</li>
                <li>OpenFeign</li>
                <li>MinIO</li>
                <li>Swagger (see swagger open-api files)</li>
                <li>Maven</li>
                <li>Docker</li>
            </ul>
            <hr/>
            <h3>Structure and description</h3>
            <hr/>
            <p>
                The project is based on the microservices architecture, each of which has
                its own database (where required) and its purpose for business logic. Such
                a construction of the system makes it possible to facilitate its scalability
                and expansion of functionality by adding instances of the developed services
                or writing new ones, respectively.
            </p>
            <hr/>
            <p>
                The full list of currently implemented micro services:
            </p>
            <ul>
                <li>User-service</li>
                <li>Task-service</li>
                <li>Audit-service</li>
                <li>Report-service</li>
                <li>Notification-service</li>
            </ul>
            <hr/>
        </div>
    )
}
export default Info;