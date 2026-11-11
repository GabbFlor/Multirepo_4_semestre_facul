'use client';

import { ImageCard, Template } from "@/components";
import { Image } from "@/resources/image/image.resource";
import { useImageService } from "@/resources/image/image.service";
import { useState } from "react";

export default function GaleriaPage() {
    const useService = useImageService();
    const [images, setImages] = useState<Image[]>([]);

    async function searchImages() {
        const result = await useService.search();
        setImages(result);
        console.table(result);
    }

    function renderImageCard(image: Image) {
        return (
            <ImageCard key={image.url}
                title={image.name}
                src={image.url}
                size={image.size}
                dateUpload={image.uploadDate}
            />
        )
    }

    function renderImageCards() {
        //return images.map(image => renderImageCard(image));
        return images.map(renderImageCard);
    }

    return (
        <Template>
            <h1>Galeria</h1>

            <button onClick={searchImages}>Clique para mudar</button>
            <section className="grid grid-cols-4 gap-8">
                {
                    renderImageCards()
                }
            </section>
        </Template>
    )
}