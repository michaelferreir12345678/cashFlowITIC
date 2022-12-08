import './App.css';
import Servico from './components/servico/Servico';
import Navbar from './components/Navbar/Navbar';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import Despesa from './components/servico/Despesa';

function App() {
  return (
    <div>
      <Navbar/>
      <main>
        <Router>
          <Routes>
            <Route path='/receita' element={<Servico/>}/>
            <Route path='/despesa' element={<Despesa/>}/>
          </Routes>
        </Router>      
      </main>  
    </div>
  );
}
export default App;