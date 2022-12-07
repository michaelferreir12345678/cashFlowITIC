import './App.css';
import Servico from './components/servico/Servico';
import Navbar from './components/Navbar/Navbar';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'

function App() {
  return (
    <div>
      <Navbar/>
      <main>
        <Router>
          <Routes>
            <Route path='/home' element={<Servico/>}/>
          </Routes>
        </Router>
        
      </main>  
    </div>
  );
}
export default App;