'use client';

interface PrimeiroComponentProps {
    mensagem: string;
    mensagemBotao: string;
}

export function PrimeiroComponent({ mensagem, mensagemBotao }: PrimeiroComponentProps) {
    function handleClick() {
        console.log("Botão clicado!");
    }

    return (
        <div>
            <h1>Primeiro Componente</h1>
            <p>{mensagem}</p>
            <button onClick={handleClick}>{mensagemBotao}</button>
        </div>
    );
}

export const ArrowFunction = () => {
    return (
        <h2>Arrow function</h2>
    )
}