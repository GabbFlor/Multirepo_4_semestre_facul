'use client';

import { ImageCard, Template } from "@/components";
import { useState } from "react";

export default function GaleriaPage() {
    const image1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXAc0RxGrr5HiyjpeFOD7yWs6VhOvozLVhgw1RWU0PqthHLuNpOxgy9jeu&s=10";
    const image2 = "https://m.media-amazon.com/images/I/71veNz-BVNL.jpg";

    const nome1 = "Placa de vídeo";
    const nome2 = "SSD";

    const [codigoImagem, setCodigoImagem] = useState<number>(1);
    const [urlImagem, setUrlImagem] = useState<string>(image1);
    const [nomeImagem, setNomeImagem] = useState<string>(nome1);

    function mudarImagem() {
        if (codigoImagem === 1) {
            setCodigoImagem(2);
            setUrlImagem(image2);
            setNomeImagem(nome2);
        } else {
            setCodigoImagem(1);
            setUrlImagem(image1);
            setNomeImagem(nome1);
        }
    }

    return (
        <Template>
            <h1>Galeria</h1>

            <button onClick={mudarImagem}>Clique para mudar</button>
            <section className="grid grid-cols-4 gap-8">
                <ImageCard src={urlImagem} title={nomeImagem} size="5mb" dateUpload="2023-01-01" />
                <ImageCard src={urlImagem} title={nomeImagem} size="5mb" dateUpload="2023-01-02" />
                <ImageCard src={urlImagem} title={nomeImagem} size="5mb" dateUpload="2023-01-03" />
                <ImageCard src={urlImagem} title={nomeImagem} size="5mb" dateUpload="2023-01-04" />
            </section>
        </Template>
    )
}