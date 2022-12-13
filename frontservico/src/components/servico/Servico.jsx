import React, { useEffect, useState } from 'react';
// import './Servico.css';
import axios from 'axios';

function Servico() {

  const [servico, setServico] = useState({ dataReceita: '', descricaoReceita: '', valorReceita: '' });
  const [servicos, setServicos] = useState([]);
  const [atualizar, setAtulizar] = useState();



  useEffect(() => {
    buscarTodos()
  }, [atualizar]);

  function buscarTodos() {
    axios
      .get('http://localhost:8080/api/receitas/')
      .then(result => {
        setServicos(result.data);
      });
  }

  function importExcel() {
    axios
      .get('http://localhost:8080/api/receitas/import/excel')
      .then(result => {
        setServicos(result.data);
      });
  }

  function handleChange(event) {
    setServico({ ...servico, [event.target.name]: event.target.value })
  }

  function limpar() {
    setServico({ dataReceita: '', descricaoReceita: '', descricaoServico: '', valorReceita: '' });


  }

  function dataBDFormatar(date){
    var strData = date.substr(8, 2)+"/"+date.substr(5, 2)+"/"+date.substr(0, 4);
      return strData;
  }

  function handleSubmit(event) {
    event.preventDefault();
    if (servico.id === undefined) {
      console.log("inserir")
      axios
        .post('http://localhost:8080/api/receitas/', servico)
        .then(result => {
          setAtulizar(result);
        });
    } else {
      axios
        .put('http://localhost:8080/api/receitas/', servico)
        .then(result => {
          setAtulizar(result);
        });
    }
    limpar();
  }

  function excluir(id) {
    axios.delete('http://localhost:8080/api/receitas/' + id).then(result => {
      setAtulizar(result);
    });
  }


  return (
    <div className="container" >
      <h1> Receitas </h1>
      <form onSubmit={handleSubmit}>
        <div className="col-6">
          <div>
            <label className="form-label">Descrição da receita</label>
            <input
              onChange={handleChange}
              value={servico.descricaoReceita}
              name="descricaoReceita"
              type="text"
              className="form-control" />
          </div>

          <div>
            <label className="form-label">Valor receita</label>
            <input 
            onChange={handleChange} 
            value={servico.valorReceita || ''} 
            name="valorReceita" 
            type="number" 
            className="form-control" />
          </div>

          <div>
            <label className="form-label">Data</label>
            <input 
            onChange={handleChange} 
            value={servico.dataReceita || ''} 
            name="dataReceita" 
            type="date" 
            className="form-control" />
          </div>

          <br />

          <input type="submit" className='btn btn-success' value="Cadastrar" ></input>

        </div>
      </form>
      <hr /><hr />

      
      <button onClick={buscarTodos} type='button' class='btn btn-primary'>Listar todos </button> &nbsp;&nbsp;
      <button onClick={importExcel} type='button' class='btn btn-secondary'>Importar XLMS</button> &nbsp;&nbsp;
      <button type='button' class='btn btn-secondary'>Exportar XLMS</button>


      <table class="table">
        <thead>
          <tr>
            <th scope="col">Data da Receita</th>
            <th scope="col">Descrição da receita</th>
            <th scope="col">Valor</th>
            <th scope="col">Status</th>
            <th scope="col">Opções</th>

            {/* dataReceita: '', descricaoReceita: '', descricaoServico: '', valorReceita: '' */}

          </tr>
        </thead>
        <tbody>
          {servicos.map(serv => (
            <tr key={serv.id}>
              <td>{dataBDFormatar(serv.dataReceita)}</td>
              <td>{serv.descricaoReceita}</td>
              <td>{serv.valorReceita}</td>
              <td>{serv.status}</td>
              <td>
                  <button onClick={() => setServico(serv)} className='btn btn-primary'>Alterar</button>&nbsp;&nbsp;
                  <button onClick={() => excluir(serv.id)} className='btn btn-danger'>Excluir</button>&nbsp;&nbsp;
              </td>
            </tr>
          ))
          }

        </tbody>
      </table>

    </div>

  );
}

export default Servico;
