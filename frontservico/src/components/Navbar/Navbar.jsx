
import './Navbar.css';

function Navbar() {
    return (
        <nav class="navbar navbar-dark bg-primary">
            <a class="navbar-brand" href="#">Fluxo de caixa - ITIC</a>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link"> Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Cadastrar
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item">Receitas</a>
                            <a class="dropdown-item">Despesas</a>
                        </div>
                    </li>
                    
                </ul>
            </div>
        </nav>

    );
}

export default Navbar;
