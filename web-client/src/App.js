import './App.css';
import Header from "./component/header/Header";
import NavBar from "./component/navbar/NavBar";
import { Route, Routes} from "react-router-dom";
import Profile from "./component/profile/Profile";
import Projects from "./component/projects/Projects";
import Tasks from "./component/tasks/Tasks";
import Audit from "./component/audit/Audit";
import Login from "./component/login/Login";
import Users from "./component/users/Users";

function App() {
  return (
      <div className="app-wraper">
        <Header/>
        <NavBar/>
          <div className="app-wraper-content">
              <Routes>
                  <Route path='/' element={<Login/>}/>
                  <Route path='/profile' element={<Profile/>}/>
                  <Route path='/projects' element={<Projects/>}/>
                  <Route path='/tasks' element={<Tasks/>}/>
                  <Route path='/audit' element={<Audit/>}/>
                  <Route path='/users' element={<Users/>}/>
              </Routes>
          </div>
      </div>
  )
}

export default App;
