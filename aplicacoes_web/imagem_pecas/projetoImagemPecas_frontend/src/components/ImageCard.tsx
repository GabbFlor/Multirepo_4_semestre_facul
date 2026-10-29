interface ImageCardProps {
  src?: string;
  title?: string;
  size?: string;
  dateUpload?: string;
}

export const ImageCard = ({ src, title, size, dateUpload }: ImageCardProps) => {
  return (
    <div className="card relative bg-white shadow-md rounded-lg transition-transform ease-in duration-300 transform hover:shadow-lg hover:translate-y-2">
      <img 
        src={src} 
        alt={title} 
        className="h-56 w-full object-cover rounded-t-md" 
    />
      <div className="image-card-content">
        <h5 className="text-lg font-semibold mb-2 text-gray-600">{title}</h5>
        <p className="text-gray-600">{size}</p>
        <p className="text-gray-600">{dateUpload}</p>
      </div>
    </div>
  );
}