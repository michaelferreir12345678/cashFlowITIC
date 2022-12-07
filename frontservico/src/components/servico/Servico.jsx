import React, { useEffect, useState } from 'react';
import './Servico.css';
import axios from 'axios';

function Servico() {

  const [servico, setServico] = useState({ valorServico: '', nomeCliente: '', dataInicio: '', dataTermino: '', descricaoServico: '', valorPago: '', dataPagamento: '' })
  const [servicos, setServicos] = useState([]);
  const [atualizar, setAtulizar] = useState();



  useEffect(() => {
    buscarTodos()
  }, [atualizar]);

  function buscarTodos(){
    axios
    .get('http://localhost:8080/api/servico/')
    .then(result => {
      setServicos(result.data);
    });
  }

  function buscarPagamentoPendente(){
    axios
    .get('http://localhost:8080/api/servico/pagamentoPendente')
    .then(result => {
      setServicos(result.data);
    });
  }

  function buscarCancelados(){
    axios
    .get('http://localhost:8080/api/servico/cancelados')
    .then(result => {
      setServicos(result.data);
    });
  }

  function handleChange(event) {
    setServico({ ...servico, [event.target.name]: event.target.value })
  }

  function limpar() {
    setServico({ valorServico: '', nomeCliente: '', dataInicio: '', dataTermino: '', descricaoServico: '', valorPago: '', dataPagamento: '' });


  }

  function handleSubmit(event) {
    event.preventDefault();
    if (servico.id == undefined) {
      console.log("inserir")
      axios
        .post('http://localhost:8080/api/servico/', servico)
        .then(result => {
          setAtulizar(result);
        });
    } else {
      axios
        .put('http://localhost:8080/api/servico/', servico)
        .then(result => {
          setAtulizar(result);
        });
    }
    limpar();
  }

  function excluir(id){
    axios.delete('http://localhost:8080/api/servico/'+id).then(result => {
      setAtulizar(result);
    });
  }

    function cancelar(id){
    axios.post('http://localhost:8080/api/servico/'+id).then(result => {
      setAtulizar(result);
    });
  }

  return (
    <div className="container" >
      <h1>Cadastro de serviços</h1>
      <form onSubmit={handleSubmit}>
        <div className="col-6">
          <div>
            <label className="form-label">Nome do cliente</label>
            <input
              onChange={handleChange}
              value={servico.nomeCliente}
              name="nomeCliente"
              type="text"
              className="form-control" />
          </div>

          <div>
            <label className="form-label">Data de Início</label>
            <input onChange={handleChange} value={servico.dataInicio || ''} name="dataInicio" type="date" className="form-control" />
          </div>

          <div>
            <label className="form-label">Data de término</label>
            <input onChange={handleChange} value={servico.dataTermino || ''} name="dataTermino" type="date" className="form-control" />
          </div>

          <div>
            <label className="form-label">Descrição</label>
            <input onChange={handleChange} value={servico.descricaoServico || ''} name="descricaoServico" type="text" className="form-control" />
          </div>

          <div>
            <label className="form-label">Valor do serviço</label>
            <input onChange={handleChange} value={servico.valorServico || ''} name="valorServico" type="number" className="form-control" />
          </div>

          <div>
            <label className="form-label">Valor pago</label>
            <input onChange={handleChange} value={servico.valorPago || ''} name="valorPago" type="number" className="form-control" />
          </div>

          <div>
            <label className="form-label">Data do pagamento</label>
            <input onChange={handleChange} value={servico.dataPagamento || ''} name="dataPagamento" type="date" className="form-control" />
          </div>
          <br />

          <input type="submit" className='btn btn-success' value="Cadastrar" ></input>

        </div>
      </form>
      <hr /><hr />

      <button onClick={buscarTodos} type='button' class='btn btn-primary'>Listar todos </button> &nbsp;&nbsp;
      <button onClick={buscarPagamentoPendente} type='button' class='btn btn-secondary'> Pagamentos pendentes </button>&nbsp;&nbsp;
      <button onClick={buscarCancelados} type='button' class='btn btn-success'>Serviços cancelados</button>


      <table class="table">
        <thead>
          <tr>
            <th scope="col">Nome Cliente</th>
            <th scope="col">Descrição do serviço</th>
            <th scope="col">Valor</th>
            <th scope="col">Status</th>
            <th scope="col">Opções</th>

          </tr>
        </thead>
        <tbody>
          {servicos.map(serv => (
            <tr key={serv.id}>
              <td>{serv.nomeCliente}</td>
              <td>{serv.descricaoServico}</td>
              <td>{serv.valorServico}</td>
              <td>{serv.status}</td>
              <td>
                {serv.status != "cancelado" &&
                  <button onClick={() => setServico(serv)} className='btn btn-primary'>Alterar</button>
                } &nbsp;&nbsp;
                {serv.status != "cancelado" &&
                  <button onClick={() => excluir(serv.id)} className='btn btn-warning'>Excluir</button>
                }&nbsp;&nbsp;
                <button onClick={() => cancelar(serv.id)} className='btn btn-danger'>Cancelar</button>
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
