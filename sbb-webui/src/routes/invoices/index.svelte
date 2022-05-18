<script>
    // populated with data from the endpoint
    export let invoices;
</script>

<h1>Number of invoices {invoices.length}</h1>

<table>
    <tr>
        <th>ID</th>
        <th>Client</th>
        <th>From</th>
        <th>To</th>
        <th>Amount</th>
    </tr>
    {#each invoices as invoice}
    <tr>
        <td>{invoice.id}</td>
        <td>{invoice.client}</td>
        <td>{invoice.interval.from}</td>
        <td>{invoice.interval.to}</td>
        <td>${invoice.amount}</td>
    </tr>
    {/each}
</table>
