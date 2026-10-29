import {Image} from "@/resources/image/image.resource";

class ImageService {
    baseURL: string = "http://localhost:8080/v1/images"

    async search() : Promise<Image[]> {
        const response = await fetch(this.baseURL);
        return await response.json();
    }
}

export const useImageService = () => new ImageService();