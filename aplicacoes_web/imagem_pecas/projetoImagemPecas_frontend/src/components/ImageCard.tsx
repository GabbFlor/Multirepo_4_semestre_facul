interface ImageCardProps {
  src?: string;
  title?: string;
  size?: number;
  dateUpload?: string;
  extension?: string;
}

export const ImageCard = ({ src, title, size, dateUpload, extension }: ImageCardProps) => {
  
  function download() {
    window.open(src, "_blank")
  }
  
  return (
    <div className="card relative bg-white shadow-md rounded-lg transition-transform ease-in duration-300 transform hover:shadow-lg hover:translate-y-2">
      <img 
        onClick={download}
        src={src} 
        alt={title} 
        className="h-56 w-full object-cover rounded-t-md" 
    />
      <div className="image-card-content">
        <h5 className="text-lg font-semibold mb-2 text-gray-600">{title}</h5>
        <p className="text-gray-600">{extension}</p>
        <p className="text-gray-600">{formatBytes(size, 2)}</p>
        <p className="text-gray-600">{dateUpload}</p>
      </div>
    </div>
  );
}

function formatBytes(bytes: number = 0, decimals = 2) {
  if (!+bytes) return "0 Bytes";

  const k = 1024;
  const dm = decimals < 0 ? 0 : decimals;
  const sizes = ['Bytes', 'KB', 'MB', 'GB']

  const i = Math.floor(Math.log(bytes) / Math.log(k));

  return `${parseFloat((bytes / Math.pow(k, i)).toFixed(dm))} ${sizes[i]}`
}