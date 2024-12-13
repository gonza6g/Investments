import { useState, useEffect } from 'react';

function App() {
  const [portfolio, setPortfolio] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    console.log('Fetching portfolio data...')
    fetch('http://localhost:8080/api/portfolio')
      .then(response => {
        console.log('Response status:', response.status)
        if (!response.ok) {
          throw new Error(`Network response was not ok: ${response.status}`)
        }
        return response.json()
      })
      .then(data => {
        console.log('Received data:', data)
        setPortfolio(data)
        setLoading(false)
      })
      .catch(error => {
        console.error('Fetch error:', error)
        setError(error.message)
        setLoading(false)
      })
  }, [])

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  if (!portfolio) return <div>No data found</div>;

  return (
    <div>
      <h1>Investment Portfolio</h1>
      <div style={{ margin: '20px', padding: '20px', border: '1px solid #ccc' }}>
        <pre>{JSON.stringify(portfolio, null, 2)}</pre>
      </div>
    </div>
  );
}

export default App;
