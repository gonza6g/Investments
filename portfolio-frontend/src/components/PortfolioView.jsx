import React, { useState } from 'react';
import './PortfolioView.css';

function PortfolioView({ portfolio, lastUpdate }) {
  const [sortConfig, setSortConfig] = useState({
    key: null,
    direction: 'ascending'
  });

  const sortData = (key) => {
    let direction = 'ascending';
    if (sortConfig.key === key && sortConfig.direction === 'ascending') {
      direction = 'descending';
    }
    setSortConfig({ key, direction });
  };

  const getSortedData = () => {
    if (!sortConfig.key) return portfolio;

    return [...portfolio].sort((a, b) => {
      let aValue = a[sortConfig.key];
      let bValue = b[sortConfig.key];
      
      // Handle numeric values
      if (typeof aValue === 'number' && typeof bValue === 'number') {
        return sortConfig.direction === 'ascending' 
          ? aValue - bValue 
          : bValue - aValue;
      }
      
      // Handle string values
      aValue = aValue?.toString().toLowerCase() ?? '';
      bValue = bValue?.toString().toLowerCase() ?? '';
      
      return sortConfig.direction === 'ascending'
        ? aValue.localeCompare(bValue)
        : bValue.localeCompare(aValue);
    });
  };

  const getSortIcon = (key) => {
    if (sortConfig.key !== key) return '↕️';
    return sortConfig.direction === 'ascending' ? '↑' : '↓';
  };

  return (
    <>
      <div className="last-update">
        Last Updated: {lastUpdate || 'Never updated'}
      </div>
      <table className="portfolio-table">
        <thead>
          <tr>
            <th onClick={() => sortData('name')}>
              Name {getSortIcon('name')}
            </th>
            <th onClick={() => sortData('symbol')}>
              Symbol {getSortIcon('symbol')}
            </th>
            <th onClick={() => sortData('price')}>
              Price {getSortIcon('price')}
            </th>
            <th onClick={() => sortData('holdings')}>
              Holdings {getSortIcon('holdings')}
            </th>
            <th onClick={() => sortData('value')}>
              Value {getSortIcon('value')}
            </th>
            <th onClick={() => sortData('change24h')}>
              24h Change {getSortIcon('change24h')}
            </th>
          </tr>
        </thead>
        <tbody>
          {getSortedData().map((asset, index) => (
            <tr key={index}>
              <td>
                <div className="asset-name">
                  {asset.name}
                </div>
              </td>
              <td className="symbol">{asset.symbol}</td>
              <td className="price-value">
                ${asset.price.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}
              </td>
              <td className="holdings">
                {asset.holdings.toLocaleString('en-US', { minimumFractionDigits: 2 })}
              </td>
              <td className={`price-value ${asset.value >= 0 ? 'value-positive' : 'value-negative'}`}>
                ${Math.abs(asset.value).toLocaleString('en-US', { minimumFractionDigits: 2 })}
              </td>
              <td className={asset.change24h >= 0 ? 'positive-change' : 'negative-change'}>
                {asset.change24h > 0 ? '+' : ''}{asset.change24h.toFixed(2)}%
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}

export default PortfolioView; 