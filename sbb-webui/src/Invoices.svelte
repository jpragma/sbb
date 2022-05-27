<script>
    let invoices = []
    fetch('http://localhost:8888/invoices')
        .then((response) => response.json())
        .then((data) => invoices = data)
</script>

<div>
    {#each invoices as invoice}
        <li>
            {invoice.id} - {invoice.amount}
        </li>
    {/each}
</div>