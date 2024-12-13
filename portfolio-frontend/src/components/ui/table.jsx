function Table({ className, ...props }) {
  return (
    <div className="relative w-full overflow-auto">
      <table className={`w-full caption-bottom text-sm ${className}`} {...props} />
    </div>
  )
}

function TableHeader(props) {
  return <thead className="border-b border-gray-800" {...props} />
}

function TableBody(props) {
  return <tbody className="[&_tr:last-child]:border-0" {...props} />
}

function TableRow(props) {
  return <tr className="border-b border-gray-800 transition-colors" {...props} />
}

function TableHead(props) {
  return <th className="h-12 px-4 text-left align-middle font-medium text-gray-400" {...props} />
}

function TableCell(props) {
  return <td className="p-4 align-middle" {...props} />
}

export { Table, TableHeader, TableBody, TableRow, TableHead, TableCell } 