import './App.css';
import Header from "./component/header/Header";
import Footer from "./component/footer/Footer";
import Body from "./component/body/Body";

function App() {
  return (
      <div className="app-wraper">
        <Header/>
        <Body/>
        <Footer/>
      </div>
  )
}

export default App;
