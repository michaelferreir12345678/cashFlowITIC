import React, { useEffect, useState } from 'react';
// import './Servico.css';
import axios from 'axios';

function Despesa() {

    const [despesa, setDespesa] = useState({ descricaoDespesa: '', dataDespesa: '', valorDespesa: '' });
    const [despesas, setDespesas] = useState([]);
    const [atualizar, setAtulizar] = useState();



    useEffect(() => {
        buscarTodos()
    }, [atualizar]);

    function buscarTodos() {
        axios
            .get('http://localhost:8080/api/despesas/')
            .then(result => {
                setDespesas(result.data);
            });
    }


    function handleChange(event) {
        setDespesa({ ...despesa, [event.target.name]: event.target.value })
    }

    function limpar() {
        setDespesa({ descricaoDespesa: '', dataDespesa: '', descricaoServico: '', valorDespesa: '' });


    }

    function handleSubmit(event) {
        event.preventDefault();
        if (despesa.id === undefined) {
            console.log("inserir")
            axios
                .post('http://localhost:8080/api/despesas/', despesa)
                .then(result => {
                    setAtulizar(result);
                });
        } else {
            axios
                .put('http://localhost:8080/api/despesas/', despesa)
                .then(result => {
                    setAtulizar(result);
                });
        }
        limpar();
    }

    function excluir(id) {
        axios.delete('http://localhost:8080/api/despesas/' + id).then(result => {
            setAtulizar(result);
        });
    }

    return (
        <div className="container" >
            <h1> Despesas </h1>
            <form onSubmit={handleSubmit}>
                <div className="col-6">
                    <div>
                        <label className="form-label">Descrição da despesa</label>
                        <input
                            onChange={handleChange}
                            value={despesa.descricaoDespesa}
                            name="descricaoDespesa"
                            type="text"
                            className="form-control" />
                    </div>

                    <div>
                        <label className="form-label">Valor despesa</label>
                        <input
                            onChange={handleChange}
                            value={despesa.valorDespesa || ''}
                            name="valorDespesa"
                            type="number"
                            className="form-control" />
                    </div>

                    <div>
                        <label className="form-label">Data</label>
                        <input
                            onChange={handleChange}
                            value={despesa.dataDespesa || ''}
                            name="dataDespesa"
                            type="date"
                            className="form-control" />
                    </div>

                    <br />

                    <input type="submit" className='btn btn-success' value="Cadastrar" ></input>

                </div>
            </form>
            <hr /><hr />

            <button onClick={buscarTodos} type='button' class='btn btn-primary'>Listar todos </button> &nbsp;&nbsp;

            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Descrição da despesa</th>
                        <th scope="col">Valor</th>
                        <th scope="col">Status</th>
                        <th scope="col">Opções</th>

                        {/* dataReceita: '', descricaoReceita: '', descricaoServico: '', valorReceita: '' */}

                    </tr>
                </thead>
                <tbody>
                    {despesas.map(serv => (
                        <tr key={serv.id}>
                            <td>{serv.descricaoDespesa}</td>
                            <td>{serv.valorDespesa}</td>
                            <td>{serv.status}</td>
                            <td>
                                <button onClick={() => setDespesa(serv)} className='btn btn-primary'>Alterar</button>&nbsp;&nbsp;
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

export default Despesa;
