import logo from './logo.svg';
import './App.css';
import axios from 'axios';

function axiosTest() {
  axios.post('/test')
    .then(function(res){
        alert(res.data)
    })
}

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        <button onClick={()=>axiosTest()}>테스트</button>
      </header>
    </div>
  );
}

export default App;
