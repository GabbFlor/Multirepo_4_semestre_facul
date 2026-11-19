'use client';

import { ImageCard, Template } from "@/components";
import { Image } from "@/resources/image/image.resource";
import { useImageService } from "@/resources/image/image.service";
import Link from "next/link";
import { useState } from "react";

export default function GaleriaPage() {
    const useService = useImageService();
    const [images, setImages] = useState<Image[]>([]);
    const [query, setQuery] = useState<string>("");
    const [extension, setExtension] = useState<string>("");
    const [loading, setLoading] = useState<boolean>(false);

    async function searchImages() {
        setLoading(true);
        await new Promise((resolve) => setTimeout(resolve, 1500));
        const result = await useService.search(query, extension);
        setImages(result);
        setLoading(false);
    }

    function renderImageCard(image: Image) {
        return (
            <ImageCard key={image.url}
                title={image.name}
                src={image.url}
                size={image.size}
                extension={image.extension}
                dateUpload={image.uploadDate}
            />
        )
    }

    function renderImageCards() {
        //return images.map(image => renderImageCard(image));
        return images.map(renderImageCard);
    }

    return (
        <Template loading={loading}>
            <section className="felx flex-col items-center justify-center my-3">
                <div className="flex space-x-4">
                    <input 
                        type="text" 
                        className="border px-5 py-2 rounded-lg text-gray-900"
                        onChange={e => setQuery(e.target.value)}
                    />
                    <select className="border px-4 py-2 rounded-lg text-gray-900"onChange={e => setExtension(e.target.value)}>
                        <option value="">All formats</option>
                        <option value="PNG">PNG</option>
                        <option value="JPEG">JPEG</option>
                        <option value="GIF">GIF</option>
                    </select>
                    <button className="bg-red-500 hover:bg-red-300 text-white px-4 py-2 rounded-lg" onClick={searchImages}>Search</button>
                    <Link href="/formulario">
                        <button className="bg-yellow-500 hover:bg-yellow-300 text-white px-4 py-2 rounded-lg">Add New</button>
                    </Link>
                </div>
            </section>

            
            <section className="grid grid-cols-4 gap-8">
                {
                    renderImageCards()
                }
            </section>
        </Template>
    )
}